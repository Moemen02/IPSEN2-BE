package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.ArticlePriceDAO;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlePriceService {

    private final ArticlePriceDAO articlePriceDAO;

    public ArticlePriceService(final ArticlePriceDAO articlePriceDAO) {
        this.articlePriceDAO = articlePriceDAO;
    }

    public List<ArticlePrice> getAllArticlePrices(){
        return articlePriceDAO.getAllArticlePrices();
    }
}
