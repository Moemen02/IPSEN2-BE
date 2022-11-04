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

    public Optional<Article> updateArticle(Long id, Optional<Article> article) throws ChangeSetPersister.NotFoundException {
        Optional<Article> oldArticleById = articleRepository.findById(id);
        if (oldArticleById.isPresent()){
            Article oldArticle = oldArticleById.get();
            Article newArticle = article.get();

            newArticle.setPriceid((newArticle.getPriceid() == null) ? oldArticle.getPriceid() : newArticle.getPriceid());
            newArticle.setEancode((newArticle.getEancode() == null || newArticle.getEancode().equals("")) ? oldArticle.getEancode() : newArticle.getEancode());
            newArticle.setComposition((newArticle.getComposition() == null || newArticle.getComposition().equals("")) ? oldArticle.getComposition() : newArticle.getComposition());
            newArticle.setWashsymbol((newArticle.getWashsymbol() == null || newArticle.getWashsymbol().equals("")) ? oldArticle.getWashsymbol() : newArticle.getWashsymbol());
            newArticle.setColor((newArticle.getColor() == null || newArticle.getColor().equals("")) ? oldArticle.getColor() : newArticle.getColor());
            newArticle.setLayout((newArticle.getLayout() == null || newArticle.getLayout().equals("")) ? oldArticle.getLayout() : newArticle.getLayout());
            newArticle.setArticleId((newArticle.getArticleId() == null) ? oldArticle.getArticleId() : newArticle.getArticleId());

            articleRepository.setArticleInfoById(
                    newArticle.getPriceid(),
                    newArticle.getEancode(),
                    newArticle.getComposition(),
                    newArticle.getWashsymbol(),
                    newArticle.getColor(),
                    newArticle.getLayout(),
                    newArticle.getArticleId()
            );
            articleRepository.save(newArticle);
            return article;
        }

        throw new ChangeSetPersister.NotFoundException();

    }

    public void deleteArticle(final Long id) throws ChangeSetPersister.NotFoundException {
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteArticleInfoById(id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Article addArticle(final Article article) {
        return this.articleRepository.save(article);
    }

    /**
     * This function tries to find one Article by id
     * and then tries to remove it.
     *
     * @param id given Long id to select one Article.
     * @throws ChangeSetPersister.NotFoundException if there
     * is no Article is found with the given id.
     */
    public Optional<Article> getArticle(final Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return article;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
