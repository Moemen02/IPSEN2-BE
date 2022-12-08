package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.Usage;

import java.util.List;

public interface UsageInterface {
    List<Usage> getAllUsages();
    Usage getUsageById(Long id);
    Usage addUsage(Usage usage);
}
