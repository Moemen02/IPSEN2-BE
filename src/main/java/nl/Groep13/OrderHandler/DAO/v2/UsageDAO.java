package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.UsageInterface;
import nl.Groep13.OrderHandler.model.v2.Usage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsageDAO implements UsageInterface {
    @Override
    public List<Usage> getAllUsages() {
        return null;
    }

    @Override
    public Usage getUsageById(Long id) {
        return null;
    }

    @Override
    public Usage addUsage(Usage usage) {
        return null;
    }
}
