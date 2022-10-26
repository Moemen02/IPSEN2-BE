package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Transactional
    @Query("update Article a set"
            + " a.priceid = ?1, a.detailid = ?2,"
            + " a.composition = ?3, a.washsymbol = ?4,"
            + " a.color = ?5, a.layout = ?6"
            + " where a.id = ?7")
    void setArticleInfoById (
            Long priceId,
            Long detailId,
            String composition,
            String washsymbol,
            String color,
            String layout,
            Long articleId
    );

    @Modifying
    @Transactional
    @Query(" delete from Article where id = ?1")
    void deleteArticleInfoById(Long articleId);

}
