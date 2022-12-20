package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.UsageInterface;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsageDAO {

    private final UsageRepository usageRepository;

    public UsageDAO(final UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }


    public List<Usage> getAllUsages() {
        return usageRepository.findAll();
    }


    public Usage getUsageById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Usage> usage = usageRepository.findById(id);
        if (usage.isPresent()) {
            return usage.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Usage getUsageByTypeUsage(String typeUsage) throws ChangeSetPersister.NotFoundException {
        Optional<Usage> usage = Optional.ofNullable(usageRepository.findUsageByTypeUsage(typeUsage));
        if (usage.isPresent()) {
            return usage.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Usage addUsage(final Usage usage) {
        return this.usageRepository.save(usage);
    }
}
