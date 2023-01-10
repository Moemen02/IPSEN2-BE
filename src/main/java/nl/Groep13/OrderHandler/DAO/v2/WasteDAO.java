package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteDAO {

    private final WasteRepository wasteRepository;
    private final WasteDataDAO wasteDataDAO;
    private final WasteDescriptionDAO wasteDescriptionDAO;
    private final UsageDAO usageDAO;

    public WasteDAO(WasteRepository wasteRepository, WasteDataDAO wasteDataDAO, WasteDescriptionDAO wasteDescriptionDAO, UsageDAO usageDAO) {
        this.wasteRepository = wasteRepository;
        this.wasteDataDAO = wasteDataDAO;
        this.wasteDescriptionDAO = wasteDescriptionDAO;
        this.usageDAO = usageDAO;
    }


    public List<Waste> getWaste() {
        return wasteRepository.findAll();
    }


    public Waste getWasteById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Waste> waste = wasteRepository.findById(id);
        if (waste.isPresent()) {
            return waste.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }


    public Waste updateWaste(Long id, Waste waste) throws ChangeSetPersister.NotFoundException {
        Optional<Usage> checkUsageExists = Optional.ofNullable(usageDAO.getUsageByTypeUsage(waste.getUsageID().getType_usage()));
        Optional<Waste> oldWasteById = wasteRepository.findById(id);
        if (checkUsageExists.isPresent() && oldWasteById.isPresent()) {
            Waste oldWaste = oldWasteById.get();
            Waste newWaste = waste;

            newWaste.setArticle_dataID((newWaste.getArticle_dataID() == null) ? oldWaste.getArticle_dataID() : newWaste.getArticle_dataID());
            newWaste.setArticle_descriptionID((newWaste.getArticle_descriptionID() == null) ? oldWaste.getArticle_descriptionID() : newWaste.getArticle_descriptionID());
            newWaste.setUsageID((newWaste.getUsageID() == null) ? oldWaste.getUsageID() : newWaste.getUsageID());
            newWaste.setId((newWaste.getId() == null) ? oldWaste.getId() : newWaste.getId());

            WasteData newWasteData = newWaste.getArticle_dataID();
            if (newWasteData.getId() == null) {
                newWasteData.setId(oldWaste.getArticle_dataID().getId());
            }
            WasteDescription newWasteDescription = newWaste.getArticle_descriptionID();
            if (newWasteDescription.getId() == null) {
                newWasteDescription.setId(oldWaste.getArticle_descriptionID().getId());
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

    public void addWaste(final Waste waste) throws ChangeSetPersister.NotFoundException {
        WasteData wasteData = wasteDataDAO.addWasteData(waste.getArticle_dataID());
        WasteDescription wasteDescription = wasteDescriptionDAO.addWasteDescription(waste.getArticle_descriptionID());
        Usage usage = usageDAO.getUsageByTypeUsage(waste.getUsageID().getType_usage());
        waste.setArticle_dataID(wasteData);
        waste.setArticle_descriptionID(wasteDescription);
        waste.setUsageID(usage);
        wasteRepository.save(waste);
    }
}


