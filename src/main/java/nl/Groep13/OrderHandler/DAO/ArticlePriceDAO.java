package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.repository.ArticlePriceRepository;

public class ArticlePriceDAO {

    private final ArticlePriceRepository articlePriceRepository;

    public ArticlePriceDAO(ArticlePriceRepository articlePriceRepository) {
        this.articlePriceRepository = articlePriceRepository;
    }
}
