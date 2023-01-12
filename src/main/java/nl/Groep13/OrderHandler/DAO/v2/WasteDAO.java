package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.repository.v2.ArticleRepositoryV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteDAO {

    private final ArticleRepositoryV2 wasteRepository;
    private final WasteDataDAO wasteDataDAO;
    private final WasteDescriptionDAO wasteDescriptionDAO;
    private final UsageDAO usageDAO;

    public WasteDAO(ArticleRepositoryV2 wasteRepository, WasteDataDAO wasteDataDAO, WasteDescriptionDAO wasteDescriptionDAO, UsageDAO usageDAO) {
        this.wasteRepository = wasteRepository;
        this.wasteDataDAO = wasteDataDAO;
        this.wasteDescriptionDAO = wasteDescriptionDAO;
        this.usageDAO = usageDAO;
    }


    public List<ArticleV2> getWaste() {
        return (List<ArticleV2>) wasteRepository.findAll();
    }


    public ArticleV2 getWasteById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<ArticleV2> waste = wasteRepository.findById(id);
        if (waste.isPresent()) {
            return waste.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }


    public ArticleV2 updateWaste(Long id, ArticleV2 waste) throws ChangeSetPersister.NotFoundException {
        Optional<Usage> checkUsageExists = Optional.ofNullable(usageDAO.getUsageByTypeUsage(waste.getUsageID().getType_usage()));
        Optional<ArticleV2> oldWasteById = wasteRepository.findById(id);
        if (checkUsageExists.isPresent() && oldWasteById.isPresent()) {
            ArticleV2 oldWaste = oldWasteById.get();
            ArticleV2 newWaste = waste;

            newWaste.setArticle_dataID((newWaste.getArticle_dataID() == null) ? oldWaste.getArticle_dataID() : newWaste.getArticle_dataID());
            newWaste.setArticle_descriptionID((newWaste.getArticle_descriptionID() == null) ? oldWaste.getArticle_descriptionID() : newWaste.getArticle_descriptionID());
            newWaste.setUsageID((newWaste.getUsageID() == null) ? oldWaste.getUsageID() : newWaste.getUsageID());
            newWaste.setId((newWaste.getId() == null) ? oldWaste.getId() : newWaste.getId());

            ArticleData newWasteData = newWaste.getArticle_dataID();
            if (newWasteData.getId() == null) {
                newWasteData.setId(oldWaste.getArticle_dataID().getId());
                newWasteData.setId(oldWaste.getArticle_dataID().getId());
            }
            ArticleDescription newWasteDescription = newWaste.getArticle_descriptionID();
            if (newWasteDescription.getId() == null) {
                newWasteDescription.setId(oldWaste.getArticle_descriptionID().getId());
                newWasteDescription.setId(oldWaste.getArticle_descriptionID().getId());
            }
            Usage newUsage = checkUsageExists.get();

            wasteDataDAO.updateWasteData(newWasteData.getId(), newWasteData);
            wasteDescriptionDAO.updateWasteDescription(newWasteDescription.getId(), newWasteDescription);

            wasteRepository.setWasteInfoById(
                    newUsage.getId(),
                    newWaste.getId()
            );
            return newWaste;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void addWaste(final ArticleV2 waste) throws ChangeSetPersister.NotFoundException {
        ArticleData wasteData = wasteDataDAO.addWasteData(waste.getArticle_dataID());
        ArticleDescription wasteDescription = wasteDescriptionDAO.addWasteDescription(waste.getArticle_descriptionID());
        Usage usage = usageDAO.getUsageByTypeUsage(waste.getUsageID().getType_usage());
        waste.setArticle_dataID(wasteData);
        waste.setArticle_descriptionID(wasteDescription);
        waste.setArticle_dataID(wasteData);
        waste.setArticle_descriptionID(wasteDescription);
        waste.setUsageID(usage);
        wasteRepository.save(waste);
    }
}


