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
}
