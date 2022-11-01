package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<lOrder, Long> {

    @Modifying
    @Transactional
    @Query("update lOrder lorder set " +
            "lorder.articlenumber = ?2," +
            "lorder.customerid = ?3, lorder.claimed_by = ?4" +
            " where lorder.id = ?1")
    void setOrderById(
            Long id,
            int articlenumber,
            int customerid,
            int claimed_by
    );

    @Modifying
    @Transactional
    @Query("delete from lOrder where id = ?1")
    void deletelOrderById(Long orderId);
}
