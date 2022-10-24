package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.repository.ArticleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;

    public ArticleDAO(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> getArticle(long id){
        Optional<Article> article = this.articleRepository.findById(id);
        return article;
    }
}
