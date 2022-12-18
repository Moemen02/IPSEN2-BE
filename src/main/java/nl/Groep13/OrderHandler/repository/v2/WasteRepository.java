package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {

    @Modifying
    @Transactional
    @Query("update Waste waste set"
            + " waste.UsageID.id = ?1"
            + " where waste.id = ?2")
    void setWasteInfoById (
            Long wasteUsageId,
            Long id
    );
}
