package nl.Groep13.OrderHandler.record;

import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;

public record ArticleCustomerRec(ArticleV2 article, CustomerV2 customer) {
}
