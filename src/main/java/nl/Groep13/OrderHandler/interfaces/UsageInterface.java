package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.Usage;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UsageInterface {
    List<Usage> getAllUsages();
    Usage getUsageById(Long id) throws ChangeSetPersister.NotFoundException;
    Usage addUsage(Usage usage);
}
