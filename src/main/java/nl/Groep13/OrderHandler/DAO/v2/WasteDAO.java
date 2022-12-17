package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDescriptionRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteDAO implements WasteInterface {

    private final WasteRepository wasteRepository;

    public WasteDAO(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }


    @Override
    public List<Waste> getWaste() {
        return wasteRepository.findAll();
    }

    @Override
    public Waste getWasteById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Waste> waste = wasteRepository.findById(id);
        if (waste.isPresent()) {
            return waste.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    @Override
    public Waste updateWaste(Long id, Waste waste) throws ChangeSetPersister.NotFoundException {
        Optional<Waste> oldWasteById = wasteRepository.findById(id);
        if (oldWasteById.isPresent()) {
            Waste oldWaste = oldWasteById.get();
            Waste newWaste = waste;

            newWaste.setWaste_dataID((newWaste.getWaste_dataID() == null) ? oldWaste.getWaste_dataID() : newWaste.getWaste_dataID());
            newWaste.setWaste_descriptionID((newWaste.getWaste_descriptionID() == null) ? oldWaste.getWaste_descriptionID() : newWaste.getWaste_descriptionID());
            newWaste.setUsageID((newWaste.getUsageID() == null) ? oldWaste.getUsageID() : newWaste.getUsageID());
            newWaste.setId((newWaste.getId() == null) ? oldWaste.getId() : newWaste.getId());

            wasteRepository.setWasteInfoById(
                    newWaste.getWaste_dataID().getId(),
                    newWaste.getWaste_descriptionID().getId(),
                    newWaste.getUsageID().getId(),
                    newWaste.getId()
            );
            wasteRepository.save(newWaste);
            return newWaste;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
