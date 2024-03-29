package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface ArticleOrderRepository extends JpaRepository<ArticleOrder, Long>{

    @Modifying
    @Transactional
    @Query("update ArticleOrder articleOrder SET" +
            " articleOrder.customerID = ?1," +
            " articleOrder.articleID = ?2," +
            " articleOrder.finished = ?3" +
            "  where articleOrder.id = ?4")
    void setArticleOrderById(
            CustomerV2 customerID,
            ArticleV2 articleID,
            Boolean finished,
            long id
    );

}
