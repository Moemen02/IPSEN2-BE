package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository  extends JpaRepository<Color, Long> {
}
