package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.ArticlePriceDAO;
import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlePriceService {

    private final ArticlePriceDAO articlePriceDAO;

    public ArticlePriceService(final ArticlePriceDAO articlePriceDAO) {
        this.articlePriceDAO = articlePriceDAO;
    }

    public List<ArticlePrice> getAllArticlePrices(){
        return articlePriceDAO.getAllArticlePrices();
    }

        public Optional<ArticlePrice> getArticlePriceById(Long id) throws ChangeSetPersister.NotFoundException {
        return articlePriceDAO.getArticlePriceByID(id);
    }

    public Optional<ArticlePrice> updateArticlePrice(Long id, Optional<ArticlePrice> newArticlePrice) {
        return articlePriceDAO.updateArticlePrice(id, newArticlePrice);
    }
}
