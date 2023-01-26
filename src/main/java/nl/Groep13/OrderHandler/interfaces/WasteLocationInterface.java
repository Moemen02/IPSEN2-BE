package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.ArticleLocation;

import java.util.List;
import java.util.Optional;

public interface WasteLocationInterface {

    List<ArticleLocation> getAllWasteLocations();
    Optional<ArticleLocation> getWasteLocationById(Long id);
    ArticleLocation addWasteLocation(ArticleLocation articleLocation);
    ArticleLocation updateWasteLocation(Long id, ArticleLocation wasteLocation);

    Optional<ArticleLocation> getArticleLocationByOrderId(long orderId);
}
