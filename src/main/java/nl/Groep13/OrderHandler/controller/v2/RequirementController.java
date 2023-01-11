package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.RequirementInterface;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v2/requirement")
public class RequirementController {

    private final RequirementInterface requirementInterface;

    public RequirementController(RequirementInterface requirementInterface) {
        this.requirementInterface = requirementInterface;
    }

    @GetMapping()
    public ResponseEntity<List<Requirement>> getAllRequirements(){
        return ResponseEntity.ok(this.requirementInterface.getRequirement());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Requirement>> getRequirementById(@PathVariable Long id){
        return ResponseEntity.ok(this.requirementInterface.getRequirementById(id));
    }

    public List<Requirement> getRequirements(){
        return this.requirementInterface.getRequirement();
    }

    public Optional<Requirement> getReqById(Long id){
        return this.requirementInterface.getRequirementById(id);
    }
}
