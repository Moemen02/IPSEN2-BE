package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import nl.Groep13.OrderHandler.service.V2.CategoryLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/categorylocation")
public class CategoryLocationController {

    private final CategoryLocationService categoryLocationService;
    private final RequirementController requirementController;

    Gson gson = new Gson();

    @Autowired
    public CategoryLocationController(CategoryLocationService categoryLocationService, RequirementController requirementController) {
        this.categoryLocationService = categoryLocationService;
        this.requirementController = requirementController;
    }

    @GetMapping(value = "/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<CategoryLocation>> getCategoryLocationsId(@PathVariable Long ID){
        try{
            Optional<CategoryLocation> categoryLocation = this.categoryLocationService.getCategoryLocationById(ID);
            return new ResponseEntity<>(categoryLocation, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryLocation>> getAllCategoryLocations() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                this.categoryLocationService.getAllCategoryLocations()
        );
    }

    public List<Long> getCatLocIds() throws ChangeSetPersister.NotFoundException {
        List<CategoryLocation> categoryLocations = this.categoryLocationService.getAllCategoryLocations();
        List<Long> idList = new ArrayList<>();
        categoryLocations.forEach(categoryLocation -> {
            idList.add(categoryLocation.getId());
        });
        return idList;
    }

    public List<Requirement> getCatLocationRequirements(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<CategoryLocation> categoryLocation = this.categoryLocationService.getCategoryLocationById(id);
        String requirementIds = categoryLocation.get().getRequirementID();

        Long[] requirementIdList = gson.fromJson(requirementIds, Long[].class);
        List<Requirement> allRequirements = new ArrayList<>();
        for (int x = 0; x<requirementIdList.length; x++){
            Requirement singleReq = requirementController.getReqById(requirementIdList[x]).get();
            allRequirements.add(singleReq);
        }
        return allRequirements;
    }

    //TODO UPDATE FUNCTIE NOG SCHRIJVEN


}
