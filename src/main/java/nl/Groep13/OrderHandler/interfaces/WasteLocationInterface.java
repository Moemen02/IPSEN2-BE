package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.ArticleLocation;

import java.util.List;

public interface WasteLocationInterface {

    List<ArticleLocation> getAllWasteLocations();
    ArticleLocation getWasteLocationById(Long id);
    ArticleLocation addWasteLocation(ArticleLocation wasteLocation);
    ArticleLocation updateWasteLocation(Long id, ArticleLocation wasteLocation);

}
