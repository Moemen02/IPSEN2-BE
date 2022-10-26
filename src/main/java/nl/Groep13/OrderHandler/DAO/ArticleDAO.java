package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.repository.ArticleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;

    public ArticleDAO(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public boolean updateArticle(final Article article) {
        articleRepository.setArticleInfoById(
                article.getPriceid(),
                article.getDetailid(),
                article.getComposition(),
                article.getWashsymbol(),
                article.getColor(),
                article.getLayout(),
                article.getArticleId()
        );
        return true;
    }

    public void deleteArticle(final Long id) throws ChangeSetPersister.NotFoundException {
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteArticleInfoById(id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Article addArticle(final Article article) {
        article.setArticleId(null);
        return this.articleRepository.save(article);
    }

    /**
     * This function tries to find one Article by id
     * and then tries to remove it.
     * @param id given Long id to select one Article.
     * @throws ChangeSetPersister.NotFoundException if there
     * is no Article is found with the given id.
     */
    public Article getArticle(final Long id)
        throws ChangeSetPersister.NotFoundException {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
