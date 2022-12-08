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
            + " wastedata.supplier = ?1, wastedata.productGroup = ?2,"
            + " wastedata.eancode = ?3, wastedata.colorId = ?4,"
            + " wastedata.patternLength = ?5, wastedata.patternWidth = ?6,"
            + " wastedata.compositionId = ?7, wastedata.stockRL = ?8"
            + " where wastedata.id = ?9")
    void setWasteDataInfoById(
            String supplier,
            String productGroup,
            String eancode,
            Long colorId,
            float patternLength,
            float patternWidth,
            Long compositionId,
            boolean stockRL,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from WasteData where id = ?1")
    void deleteWasteDataById(Long id);
}
