package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.repository.v2.ArticleRepositoryV2;
import nl.Groep13.OrderHandler.service.V2.AttrCopy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleDAOV2 implements ArticleInterface {

    private final ArticleRepositoryV2 articleRepositoryV2;
    private final WasteDataDAO wasteDataDAO;
    private final WasteDescriptionDAO wasteDescriptionDAO;
    private final UsageDAO usageDAO;

    public ArticleDAOV2(ArticleRepositoryV2 articleRepositoryV2, WasteDataDAO wasteDataDAO, WasteDescriptionDAO wasteDescriptionDAO, UsageDAO usageDAO) {
        this.articleRepositoryV2 = articleRepositoryV2;
        this.wasteDataDAO = wasteDataDAO;
        this.wasteDescriptionDAO = wasteDescriptionDAO;
        this.usageDAO = usageDAO;
    }

    @Override
    public List<ArticleV2> getPagedWaste(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ArticleV2> pagedResult = articleRepositoryV2.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public Optional<ArticleV2> getWasteById(Long id) {
        return articleRepositoryV2.findById(id);
    }

    @Override
    public ArticleV2 updateWaste(Long id, ArticleV2 articleV2) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Optional<Usage> checkUsageExists = Optional.ofNullable(usageDAO.getUsageByTypeUsage(articleV2.getUsageID().getType_usage()));
        Optional<ArticleV2> oldWasteById = articleRepositoryV2.findById(id);
        if (checkUsageExists.isPresent() && oldWasteById.isPresent()) {
            ArticleV2 oldWaste = oldWasteById.get();
            ArticleV2 newWaste = articleV2;

//            new AttrCopy().copyAttributes(oldWaste, newWaste);
            newWaste.setId(id);
            ArticleData newWasteData = newWaste.getArticle_dataID();
            if (newWasteData.getId() == null) {
                newWasteData.setId(oldWaste.getArticle_dataID().getId());
            }
            ArticleDescription newWasteDescription = newWaste.getArticle_descriptionID();
            if (newWasteDescription.getId() == null) {
                newWasteDescription.setId(oldWaste.getArticle_descriptionID().getId());
            }

            Usage newUsage = checkUsageExists.get();

            wasteDataDAO.updateWasteData(newWasteData.getId(), newWasteData);
            wasteDescriptionDAO.updateWasteDescription(newWasteDescription.getId(), newWasteDescription);

            articleRepositoryV2.setWasteInfoById(
                    newUsage.getId(),
                    newWaste.getId()
            );
            return newWaste;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void addArticle(final ArticleV2 articleV2) throws ChangeSetPersister.NotFoundException {
        ArticleData wasteData = wasteDataDAO.addWasteData(articleV2.getArticle_dataID());
        ArticleDescription wasteDescription = wasteDescriptionDAO.addWasteDescription(articleV2.getArticle_descriptionID());
        Usage usage = usageDAO.getUsageByTypeUsage(articleV2.getUsageID().getType_usage());
        articleV2.setArticle_dataID(wasteData);
        articleV2.setArticle_descriptionID(wasteDescription);
        articleV2.setUsageID(usage);
        articleRepositoryV2.save(articleV2);
    }
}
