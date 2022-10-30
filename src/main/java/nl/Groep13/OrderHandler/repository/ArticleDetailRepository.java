package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, String> {

    @Modifying
    @Transactional
    @Query("update ArticleDetail articleDetail set"
            + " articleDetail.productgroup = ?1, articleDetail.supplier = ?2"
            + " where articleDetail.eancode = ?3")
    void setArticleDetailByEancode (
            String eancode,
            String productgroup,
            String supplier
    );


    @Modifying
    @Transactional
    @Query(" delete from ArticleDetail where eancode = ?1")
    void deleteArticleDetailsByEancode(String eancode);
}
