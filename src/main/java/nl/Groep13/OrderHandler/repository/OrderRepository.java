package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<lOrder, Long> {
}
