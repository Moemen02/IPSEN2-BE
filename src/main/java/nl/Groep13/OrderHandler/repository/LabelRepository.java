package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Label;
import nl.Groep13.OrderHandler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByOrderid(Long orderid);



}
