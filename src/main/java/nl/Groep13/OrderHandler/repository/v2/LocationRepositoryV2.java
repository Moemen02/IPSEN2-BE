package nl.Groep13.OrderHandler.repository.v2;


import nl.Groep13.OrderHandler.model.v2.Address;
import nl.Groep13.OrderHandler.model.v2.LocationV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LocationRepositoryV2 extends JpaRepository<LocationV2, Long> {


    @Modifying
    @Transactional
    @Query("update LocationV2 location SET " +
            "location.Category_locationID = ?1," +
            "location.Composition = ?2," +
            "location.RequirementID = ?3" +
            "where location.ID = ?4")
    void setLocationById(
            Long Category_locationID,
            String Composition,
            Long RequirementID,
            long ID
    );
}
