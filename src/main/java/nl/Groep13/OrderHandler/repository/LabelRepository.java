package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.Label;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByOrderid(Long orderid);

}
