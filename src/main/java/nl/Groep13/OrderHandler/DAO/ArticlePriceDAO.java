package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.ArticlePrice;
import nl.Groep13.OrderHandler.repository.ArticlePriceRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticlePriceDAO {

    private final ArticlePriceRepository articlePriceRepository;

    public ArticlePriceDAO(ArticlePriceRepository articlePriceRepository) {
        this.articlePriceRepository = articlePriceRepository;
    }

    public List<ArticlePrice> getAllArticlePrices() {
        return articlePriceRepository.findAll();
    }

    public Optional<ArticlePrice> getArticlePriceByID(Long id) throws ChangeSetPersister.NotFoundException{
        Optional<ArticlePrice> articlePrice = articlePriceRepository.findById(id);
        if (articlePrice.isPresent()){
            return articlePrice;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
