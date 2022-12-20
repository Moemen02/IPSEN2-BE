package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryWasteDAO {
    WasteDataRepository wasteDataRepository;

    public CategoryWasteDAO(WasteDataRepository wasteDataRepository) {
        this.wasteDataRepository = wasteDataRepository;
    }

    public List<String> getDistinctByColor() {
        return wasteDataRepository.getDistinctColor();
    }


}
