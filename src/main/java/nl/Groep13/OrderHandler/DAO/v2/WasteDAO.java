package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDescriptionRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class WasteDAO implements WasteInterface {

    private final WasteRepository wasteRepository;
    private final WasteDataDAO wasteDataDAO;
    private final WasteDescriptionDAO wasteDescriptionDAO;
    private final UsageRepository usageRepository;

    public WasteDAO(WasteRepository wasteRepository, WasteDataDAO wasteDataDAO, WasteDescriptionDAO wasteDescriptionDAO, UsageRepository usageRepository) {
        this.wasteRepository = wasteRepository;
        this.wasteDataDAO = wasteDataDAO;
        this.wasteDescriptionDAO = wasteDescriptionDAO;
        this.usageRepository = usageRepository;
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
        Optional<Usage> checkUsageExists = Optional.ofNullable(usageRepository.findUsageByTypeUsage(waste.getUsageID().getType_usage()));
        Optional<Waste> oldWasteById = wasteRepository.findById(id);
        if (checkUsageExists.isPresent() && oldWasteById.isPresent()) {
            Waste oldWaste = oldWasteById.get();
            Waste newWaste = waste;

            newWaste.setWaste_dataID((newWaste.getWaste_dataID() == null) ? oldWaste.getWaste_dataID() : newWaste.getWaste_dataID());
            newWaste.setWaste_descriptionID((newWaste.getWaste_descriptionID() == null) ? oldWaste.getWaste_descriptionID() : newWaste.getWaste_descriptionID());
            newWaste.setUsageID((newWaste.getUsageID() == null) ? oldWaste.getUsageID() : newWaste.getUsageID());
            newWaste.setId((newWaste.getId() == null) ? oldWaste.getId() : newWaste.getId());

            WasteData newWasteData = newWaste.getWaste_dataID();
            if (newWasteData.getId() == null) {
                newWasteData.setId(oldWaste.getWaste_dataID().getId());
            }
            WasteDescription newWasteDescription = newWaste.getWaste_descriptionID();
            if (newWasteDescription.getId() == null) {
                newWasteDescription.setId(oldWaste.getWaste_descriptionID().getId());
            }
            Usage newUsage = checkUsageExists.get();

            wasteDataDAO.updateWasteData(newWasteData.getId(), newWasteData);
            wasteDescriptionDAO.updateWasteDescription(newWasteDescription.getId(), newWasteDescription);

            wasteRepository.setWasteInfoById(
                    newUsage.getId(),
                    newWaste.getId()
            );
            return newWaste;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}


