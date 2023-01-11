package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteLocationDAO implements WasteLocationInterface {
    @Override
    public List<ArticleLocation> getAllWasteLocations() {
        return null;
    }

    @Override
    public ArticleLocation getWasteLocationById(Long id) {
        return null;
    }

    @Override
    public ArticleLocation addWasteLocation(ArticleLocation wasteLocation) {
        return null;
    }

    @Override
    public ArticleLocation updateWasteLocation(Long id, ArticleLocation wasteLocation) {
        return null;
    }
}
