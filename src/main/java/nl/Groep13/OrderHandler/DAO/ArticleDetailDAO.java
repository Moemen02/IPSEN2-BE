package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.repository.ArticleDetailRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleDetailDAO {

    private final ArticleDetailRepository articleDetailRepository;

    public ArticleDetailDAO(final ArticleDetailRepository articleDetailRepository){
        this.articleDetailRepository = articleDetailRepository;
    }

    public List<ArticleDetail> getAllArticleDetails() {
        return articleDetailRepository.findAll();
    }

    public Optional<ArticleDetail> updateArticleDetail(String eancode, Optional<ArticleDetail> articleDetail) {
        Optional<ArticleDetail> oldArticleDetailByEancode = articleDetailRepository.findById(eancode);
        ArticleDetail oldArticleDetail = oldArticleDetailByEancode.get();
        ArticleDetail newArticleDetail = articleDetail.get();

        newArticleDetail.setEancode((newArticleDetail.getEancode()) == null ? oldArticleDetail.getEancode() : newArticleDetail.getEancode());
        newArticleDetail.setProductGroup((newArticleDetail.getProductGroup()) == null ? oldArticleDetail.getProductGroup() : newArticleDetail.getProductGroup());
        newArticleDetail.setSupplier((newArticleDetail.getSupplier()) == null ? oldArticleDetail.getSupplier() : newArticleDetail.getSupplier());

        articleDetailRepository.setArticleDetailByEancode(
                newArticleDetail.getEancode(),
                newArticleDetail.getProductGroup(),
                newArticleDetail.getSupplier()
        );
        articleDetailRepository.save(newArticleDetail);
        return Optional.of(newArticleDetail);
    }

    public Optional<ArticleDetail> getArticleDetailByEancode(String eancode) throws ChangeSetPersister.NotFoundException {
        if (articleDetailRepository.findById(eancode).isPresent()) {
            return articleDetailRepository.findById(eancode);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void deleteArticleDetail(final String eancode) throws ChangeSetPersister.NotFoundException {
        if (articleDetailRepository.findById(eancode).isPresent()) {
            articleDetailRepository.deleteArticleDetailsByEancode(eancode);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public ArticleDetail addArticleDetail(final ArticleDetail articleDetail) {
        return this.articleDetailRepository.save(articleDetail);
    }

}
