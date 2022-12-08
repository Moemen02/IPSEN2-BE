package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteDAO implements WasteInterface {

    @Override
    public List<Waste> getWaste() {
        return null;
    }

    @Override
    public Waste getWasteById(Long id) {
        return null;
    }

    @Override
    public Waste updateWaste(Long id, Waste newWasteData) {
        return null;
    }
}
