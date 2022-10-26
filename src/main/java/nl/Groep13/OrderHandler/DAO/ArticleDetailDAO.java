package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.repository.ArticleDetailRepository;
import org.springframework.stereotype.Component;

@Component
public class ArticleDetailDAO {

    private final ArticleDetailRepository articleDetailRepository;

    public ArticleDetailDAO(ArticleDetailRepository articleDetailRepository){
        this.articleDetailRepository = articleDetailRepository;
    }

}