package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.ArticleDAO;
import nl.Groep13.OrderHandler.model.Article;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    /**
     * A necessary DAO object which is used for the Controller and can clearly show most of the functions in one-liners.
     */

    private final ArticleDAO articleDAO;

    public ArticleService (final ArticleDAO articleDAO){
        this.articleDAO = articleDAO;
    }

    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    public Article getArticle(final Long id) throws ChangeSetPersister.NotFoundException {
        return articleDAO.getArticle(id);
    }

    public boolean updateArticle(final Article article) {
        return articleDAO.updateArticle(article);
    }

    public void deleteArticle(final Long id) throws ChangeSetPersister.NotFoundException {
        articleDAO.deleteArticle(id);
    }

    public Article addArticle(final Article article) {
        return this.articleDAO.addArticle(article);
    }
}
