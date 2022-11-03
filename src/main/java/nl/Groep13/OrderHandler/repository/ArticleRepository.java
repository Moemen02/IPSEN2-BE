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
    @Query("update Article article set"
            + " article.priceid = ?1, article.eancode = ?2,"
            + " article.composition = ?3, article.washsymbol = ?4,"
            + " article.color = ?5, article.layout = ?6,"
            + " article.location = ?7 where article.id = ?8")
    void setArticleInfoById (
            Long priceid,
            String eancode,
            String composition,
            String washsymbol,
            String color,
            String layout,
            String location,
            Long articleid
    );

    @Modifying
    @Transactional
    @Query(" delete from Article where id = ?1")
    void deleteArticleInfoById(Long articleId);

}
