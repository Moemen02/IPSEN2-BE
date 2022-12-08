package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {

    @Modifying
    @Transactional
    @Query(" update Composition composition set"
            + " composition.compositionName = ?1"
            + " where composition.id = ?2")
    void setComposition (
            String compositionName,
            Long id
    );

    @Modifying
    @Transactional
    @Query(" delete from Composition where id = ?1")
    void deleteCompositionById(Long id);
}
