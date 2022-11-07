package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Adress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {

}
