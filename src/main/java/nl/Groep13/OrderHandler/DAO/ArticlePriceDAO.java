package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticleDetail;
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

    public Optional<ArticlePrice> updateArticlePrice(Long id, Optional<ArticlePrice> newArticlePrice) {
        Optional<ArticlePrice> oldArticlePriceById = articlePriceRepository.findById(id);
        ArticlePrice oldArticlePrice = oldArticlePriceById.get();
        ArticlePrice inpArticlePrice = newArticlePrice.get();

        inpArticlePrice.setType((inpArticlePrice.getType() == null) ? oldArticlePrice.getType() : inpArticlePrice.getType());
        inpArticlePrice.setWidth((inpArticlePrice.getWidth() == 0) ? oldArticlePrice.getWidth() : inpArticlePrice.getWidth());
        inpArticlePrice.setPtrWidth((inpArticlePrice.getPtrWidth() == 0) ? oldArticlePrice.getPtrWidth() : inpArticlePrice.getPtrWidth());
        inpArticlePrice.setPtrLength((inpArticlePrice.getPtrLength() == 0) ? oldArticlePrice.getPtrLength() : inpArticlePrice.getPtrLength());
        inpArticlePrice.setvPrice((inpArticlePrice.getvPrice() == 0) ? oldArticlePrice.getvPrice() : inpArticlePrice.getvPrice());
        inpArticlePrice.setaPrice((inpArticlePrice.getaPrice() == 0) ? oldArticlePrice.getaPrice() : inpArticlePrice.getaPrice());
        inpArticlePrice.setDescription((inpArticlePrice.getDescription() == null) ? oldArticlePrice.getDescription() : inpArticlePrice.getDescription());
        inpArticlePrice.setId((inpArticlePrice.getId() == null) ? oldArticlePrice.getId() : inpArticlePrice.getId());

        articlePriceRepository.setArticleInfoById(
                inpArticlePrice.getType(),
                inpArticlePrice.getWidth(),
                inpArticlePrice.getPtrWidth(),
                inpArticlePrice.getPtrLength(),
                inpArticlePrice.getvPrice(),
                inpArticlePrice.getaPrice(),
                inpArticlePrice.getDescription(),
                inpArticlePrice.getId()
        );

        return Optional.of(inpArticlePrice);
    }
}
