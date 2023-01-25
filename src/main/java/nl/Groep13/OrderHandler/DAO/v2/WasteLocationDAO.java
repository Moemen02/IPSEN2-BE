package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.repository.v2.WasteLocationRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteLocationDAO implements WasteLocationInterface {

    private final WasteLocationRepository wasteLocationRepository;

    public WasteLocationDAO(WasteLocationRepository wasteLocationRepository) {
        this.wasteLocationRepository = wasteLocationRepository;
    }

    @Override
    public List<ArticleLocation> getAllWasteLocations() {
        return wasteLocationRepository.findAll();
    }


    public Optional<ArticleLocation> getWasteLocationById(final Long id) {
        return wasteLocationRepository.findById(id);
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
