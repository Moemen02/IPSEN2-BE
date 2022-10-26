package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePriceRepository extends JpaRepository<ArticlePrice, Long> {
}
