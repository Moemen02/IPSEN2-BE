package nl.Groep13.OrderHandler.record;

import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.model.v2.Usage;

public record ArticleRec(Long id, ArticleData _article_dataID, ArticleDescription _article_descriptionID, Usage usage) {
}
