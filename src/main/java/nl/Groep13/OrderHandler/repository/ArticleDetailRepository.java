package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Long> {
}
