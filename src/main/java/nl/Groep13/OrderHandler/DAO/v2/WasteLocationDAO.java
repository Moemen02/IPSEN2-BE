package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.repository.v2.WasteLocationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteLocationDAO implements WasteLocationInterface {

    private final WasteLocationRepository wasteLocationRepository;

    public WasteLocationDAO(WasteLocationRepository wasteLocationRepository) {
        this.wasteLocationRepository = wasteLocationRepository;
    }

    @Override
    public List<ArticleLocation> getAllWasteLocations() {
        return null;
    }

    @Override
    public ArticleLocation getWasteLocationById(Long id) {
        return null;
    }

    @Override
    public ArticleLocation addWasteLocation(ArticleLocation articleLocation) {
        return wasteLocationRepository.save(articleLocation);
    }

    @Override
    public ArticleLocation updateWasteLocation(Long id, ArticleLocation wasteLocation) {
        return null;
    }
}
