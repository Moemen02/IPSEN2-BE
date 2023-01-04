package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import nl.Groep13.OrderHandler.service.V2.AttrCopy;
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

    public WasteData getWasteDataById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<WasteData> wasteData = wasteDataRepository.findById(id);
        if (wasteData.isPresent()) {
            return wasteData.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public WasteData updateWasteData(Long id, WasteData newWasteData) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Optional<WasteData> oldWasteDataById = wasteDataRepository.findById(id);
        WasteData alteredWasteData = newWasteData;

        if (oldWasteDataById.isPresent()) {
            WasteData oldWasteData = oldWasteDataById.get();

            new AttrCopy().copyAttributes(oldWasteData, alteredWasteData);

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
