package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Transactional
    @Query("update Address address SET " +
            "address.housenumber = ?1," +
            "address.postal_code = ?2," +
            "address.streetname = ?3" +
            "where address.id = ?4")
    void setAddressById(
            int housenumber,
            String postal_code,
            String streetname,
            long id
    );

}
