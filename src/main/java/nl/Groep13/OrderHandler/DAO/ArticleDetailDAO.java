package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.repository.ArticleDetailRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDetailDAO {

    private final ArticleDetailRepository articleDetailRepository;

    public ArticleDetailDAO(final ArticleDetailRepository articleDetailRepository){
        this.articleDetailRepository = articleDetailRepository;
    }

    public List<ArticleDetail> getAllArticleDetails() {
        return articleDetailRepository.findAll();
    }

}
