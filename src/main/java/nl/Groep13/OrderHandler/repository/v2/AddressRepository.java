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
            "address.Housenumber = ?1," +
            "address.Postal_code = ?2," +
            "address.Streetname = ?3" +
            "where address.ID = ?4")
    void setAddressById(
            int Housenumber,
            String Postal_code,
            String Streetname,
            long ID
    );

}
