package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.repository.v2.ArticleDataRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryWasteDAO {
    ArticleDataRepository wasteDataRepository;

    public CategoryWasteDAO(ArticleDataRepository wasteDataRepository) {
        this.wasteDataRepository = wasteDataRepository;
    }

    public List<String> getDistinctByColor() {
        return wasteDataRepository.getDistinctColor();
    }


}
