package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ColorRepository  extends JpaRepository<Color, Long> {

    @Modifying
    @Transactional
    @Query(" update Color color set"
            + " color.colorName = ?1"
            + " where color.id = ?2")
    void setColor (
            String colorName,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from Color where id = ?1")
    void deleteColorById(Long id);
}
