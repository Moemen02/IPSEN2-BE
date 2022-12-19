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

import java.util.List;
import java.util.Optional;

@Component
public class WasteDAO implements WasteInterface {

    private final WasteRepository wasteRepository;
    private final WasteDataDAO wasteDataDAO;
    private final WasteDataRepository wasteDataRepository;
    private final WasteDescriptionDAO wasteDescriptionDAO;
    private final WasteDescriptionRepository wasteDescriptionRepository;
    private final UsageRepository usageRepository;


    public WasteDAO(WasteRepository wasteRepository, WasteDataDAO wasteDataDAO, WasteDescriptionDAO wasteDescriptionDAO, UsageRepository usageRepository,
                    WasteDataRepository wasteDataRepository, WasteDescriptionRepository wasteDescriptionRepository) {
        this.wasteRepository = wasteRepository;
        this.wasteDataDAO = wasteDataDAO;
        this.wasteDescriptionDAO = wasteDescriptionDAO;
        this.usageRepository = usageRepository;
        this.wasteDataRepository = wasteDataRepository;
        this.wasteDescriptionRepository = wasteDescriptionRepository;
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

    public void addWaste(final Waste waste) {
        WasteData wasteData = wasteDataRepository.save(waste.getWaste_dataID());
        WasteDescription wasteDescription = wasteDescriptionRepository.save(waste.getWaste_descriptionID());
        Usage usage = usageRepository.findUsageByTypeUsage(waste.getUsageID().getType_usage());
        waste.setWaste_dataID(wasteData);
        waste.setWaste_descriptionID(wasteDescription);
        waste.setUsageID(usage);
        System.out.println(wasteDataRepository.findById(wasteData.getId()).get());
        System.out.println(wasteDescriptionRepository.findById(wasteDescription.getId()).get());
        wasteRepository.save(waste);
    }
}


