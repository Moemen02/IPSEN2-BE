package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface WasteInterface {
    List<Waste> getWaste();
    Waste getWasteById(Long id) throws ChangeSetPersister.NotFoundException;
    Waste updateWaste(Long id, Waste newWasteData) throws ChangeSetPersister.NotFoundException;
}