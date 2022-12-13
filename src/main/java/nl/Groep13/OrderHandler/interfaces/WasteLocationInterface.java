package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.WasteLocation;

import java.util.List;

public interface WasteLocationInterface {

    List<WasteLocation> getAllWasteLocations();
    WasteLocation getWasteLocationById(Long id);
    WasteLocation addWasteLocation(WasteLocation wasteLocation);
    WasteLocation updateWasteLocation(Long id, WasteLocation wasteLocation);

}
