package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteDAO implements WasteInterface {

    private final WasteRepository wasteRepository;

    public WasteDAO(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    @Override
    public List<Waste> getWaste() {
        return wasteRepository.findAll();
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
