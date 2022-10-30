package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ArticlePriceRepository extends JpaRepository<ArticlePrice, Long> {

    @Modifying
    @Transactional
    @Query("update ArticlePrice articlePrice set"
            + " articlePrice.type = ?1, articlePrice.width = ?2,"
            + " articlePrice.ptrWidth = ?3, articlePrice.ptrLength = ?4,"
            + " articlePrice.vPrice = ?5, articlePrice.aPrice = ?6,"
            + " articlePrice.description = ?7"
            + " where articlePrice.id = ?8")
    void setArticleInfoById (
            String type,
            float width,
            float ptrWidth,
            float ptrLength,
            float vPrice,
            float aPrice,
            String description,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from ArticlePrice where id = ?1")
    void deleteArticlePriceById(Long articlePriceId);
}
