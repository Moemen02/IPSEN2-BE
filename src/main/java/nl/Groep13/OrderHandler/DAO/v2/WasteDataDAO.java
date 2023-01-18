package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.service.V2.AttrCopy;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.repository.v2.ArticleDataRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteDataDAO {
    private final ArticleDataRepository wasteDataRepository;

    public WasteDataDAO(ArticleDataRepository wasteDataRepository) {
        this.wasteDataRepository = wasteDataRepository;
    }

    public List<ArticleData> getAllWasteData() {
        return wasteDataRepository.findAll();
    }

    public ArticleData addWasteData(final ArticleData wasteData) {
        return this.wasteDataRepository.save(wasteData);
    }

    public ArticleData getWasteDataById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<ArticleData> wasteData = wasteDataRepository.findById(id);
        if (wasteData.isPresent()) {
            return wasteData.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public ArticleData updateWasteData(Long id, ArticleData newWasteData) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Optional<ArticleData> oldWasteDataById = wasteDataRepository.findById(id);
        ArticleData alteredWasteData = newWasteData;

        if (oldWasteDataById.isPresent()) {
            ArticleData oldWasteData = oldWasteDataById.get();

            new AttrCopy().copyAttributes(alteredWasteData, oldWasteData);

            wasteDataRepository.setWasteDataInfoById(
                    alteredWasteData.getSupplier(),
                    alteredWasteData.getEancode(),
                    alteredWasteData.getColor(),
                    alteredWasteData.getPatternLength(),
                    alteredWasteData.getPatternWidth(),
                    alteredWasteData.getComposition(),
                    alteredWasteData.isStockRL(),
                    alteredWasteData.getProductgroup(),
                    alteredWasteData.getId()
            );

            return alteredWasteData;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void deleteWasteDataById( Long id) throws ChangeSetPersister.NotFoundException {
        if (wasteDataRepository.findById(id).isPresent()) {
            wasteDataRepository.deleteWasteDataById(id);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
