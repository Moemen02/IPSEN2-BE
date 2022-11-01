package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Transactional
    @Query("update Customer customer SET " +
            "customer.articlenumber = ?1, customer.addressid = ?2," +
            "customer.name = ?3, customer.perserved_fabric = ?4," +
            "customer.retour_fabric = ?5 " +
            "where customer.id = ?6")
    void setCustomerById(
            int articlenumber,
            long addressid,
            String name,
            boolean perserved_fabric,
            boolean retour_fabric,
            long id

    );

    @Modifying
    @Transactional
    @Query("delete from Customer where id = ?1")
    void deleteCustomerById(Long customerId);
}