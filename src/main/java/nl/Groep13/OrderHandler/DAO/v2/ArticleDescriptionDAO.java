package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.repository.v2.ArticleDescriptionRepository;
import nl.Groep13.OrderHandler.service.V2.AttrCopy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleDescriptionDAO {
    private final ArticleDescriptionRepository wasteDescriptionRepository;

    public ArticleDescriptionDAO(ArticleDescriptionRepository wasteDescriptionRepository) {
        this.wasteDescriptionRepository = wasteDescriptionRepository;
    }

    public List<ArticleDescription> getAllWasteDescription() {
        return wasteDescriptionRepository.findAll();
    }

    public ArticleDescription addWasteDescription(final ArticleDescription wasteDescription) {
        return this.wasteDescriptionRepository.save(wasteDescription);
    }

    public ArticleDescription getWasteDescriptionById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<ArticleDescription> wasteDescription = wasteDescriptionRepository.findById(id);
        if (wasteDescription.isPresent()) {
            return wasteDescription.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public ArticleDescription updateWasteDescription(Long id, ArticleDescription newWasteDescription) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Optional<ArticleDescription> oldWasteDescriptionById = wasteDescriptionRepository.findById(id);
        ArticleDescription alteredWasteDescription = newWasteDescription;
        if (oldWasteDescriptionById.isPresent()) {
            ArticleDescription oldWasteDescription = oldWasteDescriptionById.get();

            new AttrCopy().copyAttributes(oldWasteDescription, alteredWasteDescription);

            wasteDescriptionRepository.setWasteDescriptionInfoById(
                    alteredWasteDescription.getArticlenumber(),
                    alteredWasteDescription.getDescription(),
                    alteredWasteDescription.getClothWidth(),
                    alteredWasteDescription.getType(),
                    alteredWasteDescription.getLayout(),
                    alteredWasteDescription.getWashcode(),
                    alteredWasteDescription.getWeight(),
                    alteredWasteDescription.isNot_tiltable(),
                    alteredWasteDescription.getMinimumStock(),
                    alteredWasteDescription.getId()
            );
            return alteredWasteDescription;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void deleteWasteDescriptionById(Long id) throws ChangeSetPersister.NotFoundException {
        if (wasteDescriptionRepository.findById(id).isPresent()) {
            wasteDescriptionRepository.deleteWasteDescriptionById(id);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
