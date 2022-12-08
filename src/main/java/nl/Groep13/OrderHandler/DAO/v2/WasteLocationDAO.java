package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.WasteLocation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteLocationDAO implements WasteLocationInterface {
    @Override
    public List<WasteLocation> getAllWasteLocations() {
        return null;
    }

    @Override
    public WasteLocation getWasteLocationById(Long id) {
        return null;
    }

    @Override
    public WasteLocation addWasteLocation(WasteLocation wasteLocation) {
        return null;
    }

    @Override
    public WasteLocation updateWasteLocation(Long id, WasteLocation wasteLocation) {
        return null;
    }
}
