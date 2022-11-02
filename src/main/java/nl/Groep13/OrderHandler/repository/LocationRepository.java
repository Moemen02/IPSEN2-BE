package nl.Groep13.OrderHandler.repository;


import nl.Groep13.OrderHandler.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Modifying
    @Transactional
    @Query("update Location location set"
            + " location.type_storage = ?1,"
            + " location.branch = ?2"
            + " where location.articlenumber = ?3")
    void setLocationInfoByArticleNumber (
            String type_storage,
            String branch,
            Long articlenumber
    );

    @Modifying
    @Transactional
    @Query(" delete from Location where articlenumber = ?1")
    void deleteLocationByArticlenumber(Long LocationNumber);

}
