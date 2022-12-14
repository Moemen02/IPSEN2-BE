package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepositoryV2 extends JpaRepository<CustomerV2, Long> {

    @Modifying
    @Transactional
    @Query("update CustomerV2 customer SET " +
            "customer.Name = ?1," +
            "customer.AddressID = ?2 where customer.ID = ?3")
    void setCustomerById(String Name, String AddressID, long ID);

    @Modifying
    @Transactional
    @Query("delete from Customer where id = ?1")
    void deleteCustomerById(Long customerId);
}
