package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public WasteData addWasteData(final WasteData wasteData) {
        return this.wasteDataRepository.save(wasteData);
    }

//    public WasteData updateWasteData(Long id, WasteData newWasteData) {
//        Optional<WasteData> oldWasteDataById = wasteDataRepository.findById(id);
//        WasteData alteredWasteData = newWasteData;
//        if (oldWasteDataById.isPresent()) {
//            WasteData oldWasteData = oldWasteDataById.get();
//
//            alteredWasteData.setSupplier((newWasteData.getSupplier() == null || newWasteData.getSupplier().equals("")) ? oldWasteData.getSupplier() : newWasteData.getSupplier());
//            alteredWasteData.setProductGroup((newWasteData.getProductGroup() == null || newWasteData.getProductGroup().equals("")) ? oldWasteData.getProductGroup() : newWasteData.getProductGroup());
//            alteredWasteData.setEancode((newWasteData.getEancode() == null || newWasteData.getEancode().equals("")) ? oldWasteData.getEancode() : newWasteData.getEancode());
//            alteredWasteData.setColorId((newWasteData.getColorId() == null) ? oldWasteData.getColorId() : newWasteData.getColorId());
//            alteredWasteData.setPatternLength((newWasteData.getPatternLength() == 0) ? oldWasteData.getPatternLength() : newWasteData.getPatternLength());
//            alteredWasteData.setPatternWidth((newWasteData.getPatternWidth() == 0) ? oldWasteData.getPatternWidth() : newWasteData.getPatternWidth());
//            alteredWasteData.setCompositionId((newWasteData.getCompositionId() == null) ? oldWasteData.getCompositionId() : newWasteData.getCompositionId());
//            alteredWasteData.setStockRL((newWasteData.isStockRL() != oldWasteData.isStockRL()) ? newWasteData.isStockRL() : oldWasteData.isStockRL());
//
//        }
//
//        return alteredWasteData;
//    }

    public WasteData getWasteDataById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<WasteData> wasteData = wasteDataRepository.findById(id);
        if (wasteData.isPresent()) {
            return wasteData.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
