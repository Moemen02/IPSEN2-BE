package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Address;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CategoryLocationRepository extends JpaRepository<CategoryLocation, Long> {

    @Modifying
    @Transactional
    @Query("update CategoryLocation categoryLocation SET " +
            "categoryLocation.location_type = ?1" +
            "where categoryLocation.ID = ?2")
    void setCategorylocationById(
            String Location_type,
            long ID
    );


}
