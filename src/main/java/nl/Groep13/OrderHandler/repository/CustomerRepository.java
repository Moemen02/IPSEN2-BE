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
    @Query("update Customer customer set"
            + " customer.id = ?1, customer.articlenumber = ?2,"
            + " customer.addressid = ?3, customer.name = ?4")
    void setCustomerById(
            Long id,
            int articlenumber,
            long addressid,
            String name

    );

    @Modifying
    @Transactional
    @Query("delete from Customer where id = ?1")
    void deleteCustomerById(Long customerId);
}
