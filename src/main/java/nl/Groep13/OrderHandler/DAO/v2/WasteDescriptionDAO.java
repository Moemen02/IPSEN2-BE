package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.repository.v2.WasteDescriptionRepository;
import nl.Groep13.OrderHandler.service.V2.AttrCopy;
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

    public WasteDescription updateWasteDescription(Long id, WasteDescription newWasteDescription) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Optional<WasteDescription> oldWasteDescriptionById = wasteDescriptionRepository.findById(id);
        WasteDescription alteredWasteDescription = newWasteDescription;
        if (oldWasteDescriptionById.isPresent()) {
            WasteDescription oldWasteDescription = oldWasteDescriptionById.get();

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
