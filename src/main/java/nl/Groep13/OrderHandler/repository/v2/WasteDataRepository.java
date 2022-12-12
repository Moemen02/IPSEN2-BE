package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.WasteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WasteDataRepository extends JpaRepository<WasteData, Long>{

    @Modifying
    @Transactional
    @Query("update WasteData wastedata set"
            + " wastedata.supplier = ?1, wastedata.eancode = ?2,"
            + " wastedata.color = ?3, wastedata.patternLength = ?4,"
            + " wastedata.patternWidth = ?5, wastedata.composition = ?6,"
            + " wastedata.stockRL = ?7, wastedata.productgroup = ?8"
            + " where wastedata.id = ?9")
    void setWasteDataInfoById(
            String supplier,
            String eancode,
            String color,
            float patternLength,
            float patternWidth,
            String composition,
            boolean stockRL,
            String productGroup,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from WasteData where id = ?1")
    void deleteWasteDataById(Long id);
}
