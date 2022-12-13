package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WasteDescriptionRepository extends JpaRepository<WasteDescription, Long> {

    @Modifying
    @Transactional
    @Query("update WasteDescription wastedescription set"
            + " wastedescription.articlenumber = ?1, wastedescription.description = ?2,"
            + " wastedescription.clothWidth = ?3, wastedescription.type = ?4,"
            + " wastedescription.layout = ?5, wastedescription.washcode = ?6,"
            + " wastedescription.weight = ?7, wastedescription.not_tiltable = ?8,"
            + " wastedescription.minimumStock = ?9"
            + " where wastedescription.id = ?10 ")
    void setWasteDescriptionInfoById(
            String articleNumber,
            String description,
            int clothWidth,
            String type,
            String layout,
            String washcode,
            int weight,
            boolean not_tiltable,
            int minimumStock,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from WasteDescription where id = ?1")
    void deleteWasteDescriptionById(Long id);
}
