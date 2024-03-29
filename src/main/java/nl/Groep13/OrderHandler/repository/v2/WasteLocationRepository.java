package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteLocationRepository  extends JpaRepository<ArticleLocation, Long> {

    @Query("SELECT artloc from ArticleLocation artloc where artloc.articleID = ?1")
    Optional<ArticleLocation> getArticleLocationByOrderId(Long orderId);

}
