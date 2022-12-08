package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.Waste;

import java.util.List;

public interface WasteInterface {
    List<Waste> getWaste();
    Waste getWasteById(Long id);
    Waste updateWaste(Long id, Waste newWasteData);
}
