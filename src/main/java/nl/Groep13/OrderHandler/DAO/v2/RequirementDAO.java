package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.RequirementInterface;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import nl.Groep13.OrderHandler.repository.v2.RequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RequirementDAO implements RequirementInterface {

    private final RequirementRepository requirementRepository;

    public RequirementDAO(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public List<Requirement> getRequirement() {
        return requirementRepository.findAll();
    }

    @Override
    public Optional<Requirement> getRequirementById(Long id) {
            Optional<Requirement> requirement = this.requirementRepository.findById(id);
            if (requirement.isPresent()){
                return requirement;
            }
        return requirement;
    }
}
