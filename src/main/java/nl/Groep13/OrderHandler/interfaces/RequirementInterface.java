package nl.Groep13.OrderHandler.interfaces;


import nl.Groep13.OrderHandler.model.v2.Requirement;

import java.util.List;
import java.util.Optional;

public interface RequirementInterface {

    List<Requirement> getRequirement();

    Optional<Requirement> getRequirementById(Long id);
}
