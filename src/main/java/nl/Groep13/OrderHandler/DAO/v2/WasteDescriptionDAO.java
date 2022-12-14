package nl.Groep13.OrderHandler.DAO.v2;

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

    public WasteDescription updateWasteDescription(Long id, WasteDescription newWasteDescription) throws ChangeSetPersister.NotFoundException {
        Optional<WasteDescription> oldWasteDescriptionById = wasteDescriptionRepository.findById(id);
        WasteDescription alteredWasteDescription = newWasteDescription;
        if (oldWasteDescriptionById.isPresent()) {
            WasteDescription oldWasteDescription = oldWasteDescriptionById.get();

            alteredWasteDescription.setArticlenumber((newWasteDescription.getArticlenumber() == null || newWasteDescription.getArticlenumber().equals("")) ? oldWasteDescription.getArticlenumber() : newWasteDescription.getArticlenumber());
            alteredWasteDescription.setDescription((newWasteDescription.getDescription() == null || newWasteDescription.getDescription().equals("")) ? oldWasteDescription.getDescription() : newWasteDescription.getDescription());
            alteredWasteDescription.setClothWidth((newWasteDescription.getClothWidth() == 0) ? oldWasteDescription.getClothWidth() : newWasteDescription.getClothWidth());
            alteredWasteDescription.setType((newWasteDescription.getType() == null || newWasteDescription.getType().equals("")) ? oldWasteDescription.getType() : newWasteDescription.getType());
            alteredWasteDescription.setLayout((newWasteDescription.getLayout() == null || newWasteDescription.getLayout().equals("")) ? oldWasteDescription.getLayout() : newWasteDescription.getLayout());
            alteredWasteDescription.setWashcode((newWasteDescription.getWashcode() == null || newWasteDescription.getWashcode().equals("")) ? oldWasteDescription.getWashcode() : newWasteDescription.getWashcode());
            alteredWasteDescription.setWeight((newWasteDescription.getWeight() == 0) ? oldWasteDescription.getWeight() : newWasteDescription.getWeight());
            alteredWasteDescription.setNot_tiltable((newWasteDescription.isNot_tiltable()) != oldWasteDescription.isNot_tiltable() ? newWasteDescription.isNot_tiltable() : oldWasteDescription.isNot_tiltable());
            alteredWasteDescription.setMinimumStock((newWasteDescription.getMinimumStock() == 0) ? oldWasteDescription.getMinimumStock() : newWasteDescription.getMinimumStock());
            alteredWasteDescription.setId((newWasteDescription.getId()) == null || newWasteDescription.getId().equals("") ? oldWasteDescription.getId() : newWasteDescription.getId());

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
