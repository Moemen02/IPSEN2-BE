package nl.Groep13.OrderHandler.repository.v2;


import nl.Groep13.OrderHandler.model.v2.LocationV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LocationRepositoryV2 extends JpaRepository<LocationV2, Long> {


    @Modifying
    @Transactional
    @Query("update LocationV2 location SET " +
            "location.category_locationID = ?1" +
            "where location.id = ?2")
    void setLocationById(
            Long category_locationID,
            long id
    );

    @Query("select location.id from LocationV2 location where location.category_locationID = ?1")
    LocationV2 getLocationByCategoryLocationID(Long id);

    @Query("SELECT location from LocationV2 location where location.id = ?1")
    LocationV2 getLocationByArticleLocation(Long id);
}
