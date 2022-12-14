package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.WasteOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface WasteOrderRepository extends JpaRepository<WasteOrder, Long>{

    @Modifying
    @Transactional
    @Query("update WasteOrder wasteOrder SET" +
            " wasteOrder.CustomerID = ?1," +
            " wasteOrder.WasteID = ?2," +
            " wasteOrder.Finished = ?3" +
            "  where wasteOrder.ID = ?4")
    void setWasteOrderById(
            long CustomerID,
            Long WasteID,
            Boolean Finished,
            long ID
    );

}
