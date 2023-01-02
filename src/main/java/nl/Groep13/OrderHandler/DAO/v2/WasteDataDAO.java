package nl.Groep13.OrderHandler.DAO.v2;

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

    public ArticleData updateWasteData(Long id, ArticleData newWasteData) throws ChangeSetPersister.NotFoundException {
        Optional<ArticleData> oldWasteDataById = wasteDataRepository.findById(id);
        ArticleData alteredWasteData = newWasteData;

        if (oldWasteDataById.isPresent()) {
            ArticleData oldWasteData = oldWasteDataById.get();

            alteredWasteData.setSupplier((newWasteData.getSupplier() == null || newWasteData.getSupplier().equals("")) ? oldWasteData.getSupplier() : newWasteData.getSupplier());
            alteredWasteData.setEancode((newWasteData.getEancode() == null || newWasteData.getEancode().equals("")) ? oldWasteData.getEancode() : newWasteData.getEancode());
            alteredWasteData.setColor((newWasteData.getColor() == null || newWasteData.getColor().equals("")) ? oldWasteData.getColor() : newWasteData.getColor());
            alteredWasteData.setPatternLength((newWasteData.getPatternLength() == 0) ? oldWasteData.getPatternLength() : newWasteData.getPatternLength());
            alteredWasteData.setPatternWidth((newWasteData.getPatternWidth() == 0) ? oldWasteData.getPatternWidth() : newWasteData.getPatternWidth());
            alteredWasteData.setComposition((newWasteData.getComposition() == null) ? oldWasteData.getComposition() : newWasteData.getComposition());
            alteredWasteData.setStockRL((newWasteData.isStockRL() != oldWasteData.isStockRL()) ? newWasteData.isStockRL() : oldWasteData.isStockRL());
            alteredWasteData.setProductgroup((newWasteData.getProductgroup() == null || newWasteData.getProductgroup().equals("")) ? oldWasteData.getProductgroup() : newWasteData.getProductgroup());
            alteredWasteData.setId((newWasteData.getId() == null || newWasteData.getId().equals("")) ? oldWasteData.getId() : newWasteData.getId());

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
