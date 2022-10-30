package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.ArticleDetailDAO;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticleDetail;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleDetailService {

    private final ArticleDetailDAO articleDetailDAO;


    public ArticleDetailService(ArticleDetailDAO articleDetailDAO) {
        this.articleDetailDAO = articleDetailDAO;
    }

    public List<ArticleDetail> getAllArticleDetails(){
        return articleDetailDAO.getAllArticleDetails();
    }

    public Optional<ArticleDetail> updateArticle(String eancode, Optional<ArticleDetail> newArticleDetail) {
        return articleDetailDAO.updateArticleDetail(eancode, newArticleDetail);
    }

    public Optional<ArticleDetail> getArticleDetailByEancode(String eancode) throws ChangeSetPersister.NotFoundException {
        return articleDetailDAO.getArticleDetailByEancode(eancode);
    }
    public void deleteArticleDetail(final String eancode) throws ChangeSetPersister.NotFoundException {
        articleDetailDAO.deleteArticleDetail(eancode);
    }

    public ArticleDetail addArticleDetail(final ArticleDetail articleDetail) {
        return this.articleDetailDAO.addArticleDetail(articleDetail);
    }
}
