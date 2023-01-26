package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Address;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CategoryLocationRepository extends JpaRepository<CategoryLocation, Long> {

    @Query("SELECT catloc from CategoryLocation catloc where catloc.id = ?1")
    Optional<CategoryLocation> getCategoryLocationByLocation(Long catlocId);

}
