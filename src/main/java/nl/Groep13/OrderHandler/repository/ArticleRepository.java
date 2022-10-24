package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository extends CrudRepository<Article, Integer> {

}
