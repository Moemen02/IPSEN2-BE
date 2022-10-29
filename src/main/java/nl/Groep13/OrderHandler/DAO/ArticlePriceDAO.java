package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.ArticlePrice;
import nl.Groep13.OrderHandler.repository.ArticlePriceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticlePriceDAO {

    private final ArticlePriceRepository articlePriceRepository;

    public ArticlePriceDAO(ArticlePriceRepository articlePriceRepository) {
        this.articlePriceRepository = articlePriceRepository;
    }

    public List<ArticlePrice> getAllArticlePrices() {
        return articlePriceRepository.findAll();
    }
}
