package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WasteDataDAO {
    private final WasteDataRepository wasteDataRepository;

    public WasteDataDAO(WasteDataRepository wasteDataRepository) {
        this.wasteDataRepository = wasteDataRepository;
    }

    public List<WasteData> getAllWasteData() {
        return wasteDataRepository.findAll();
    }

    public List<Object> getAllWasteDataInStock(int stockType) {
        return wasteDataRepository.getAllWasteInStock(Long.valueOf(stockType));
    }

    public WasteData addWasteData(final WasteData wasteData) {
        return this.wasteDataRepository.save(wasteData);
    }

    public WasteData getWasteDataById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<WasteData> wasteData = wasteDataRepository.findById(id);
        if (wasteData.isPresent()) {
            return wasteData.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public WasteData updateWasteData(Long id, WasteData newWasteData) throws ChangeSetPersister.NotFoundException {
        Optional<WasteData> oldWasteDataById = wasteDataRepository.findById(id);
        WasteData alteredWasteData = newWasteData;

        if (oldWasteDataById.isPresent()) {
            WasteData oldWasteData = oldWasteDataById.get();

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
