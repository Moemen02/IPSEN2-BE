package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.repository.v2.WasteDescriptionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteDescriptionDAO {
    private final WasteDescriptionRepository wasteDescriptionRepository;

    public WasteDescriptionDAO(WasteDescriptionRepository wasteDescriptionRepository) {
        this.wasteDescriptionRepository = wasteDescriptionRepository;
    }

    public List<WasteDescription> getAllWasteDescription() {
        return wasteDescriptionRepository.findAll();
    }

    public WasteDescription addWasteDescription(final WasteDescription wasteDescription) {
        return this.wasteDescriptionRepository.save(wasteDescription);
    }

    public WasteDescription getWasteDescriptionById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<WasteDescription> wasteDescription = wasteDescriptionRepository.findById(id);
        if (wasteDescription.isPresent()) {
            return wasteDescription.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
