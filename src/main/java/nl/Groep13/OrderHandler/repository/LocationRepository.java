package nl.Groep13.OrderHandler.repository;


import nl.Groep13.OrderHandler.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
