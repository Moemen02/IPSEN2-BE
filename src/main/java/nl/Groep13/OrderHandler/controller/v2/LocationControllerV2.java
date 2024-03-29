package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import nl.Groep13.OrderHandler.model.v2.LocationV2;
import nl.Groep13.OrderHandler.service.V2.LocationServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/location")
public class LocationControllerV2 {

    private final LocationServiceV2 locationServiceV2;
    private final CategoryLocationController categoryLocationController;

    Gson gson = new Gson();

    @Autowired
    public LocationControllerV2(LocationServiceV2 locationServiceV2, CategoryLocationController categoryLocationController,Gson gson) {
        this.locationServiceV2 = locationServiceV2;
        this.gson = gson;
        this.categoryLocationController = categoryLocationController;
    }


    @GetMapping(value = "/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<LocationV2>> getLocationById(@PathVariable Long ID){
        try{
            Optional<LocationV2> locationV2 = this.locationServiceV2.getLocationById(ID);
            return new ResponseEntity<>(locationV2, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationV2>> getAllLocations() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                this.locationServiceV2.getAllLocations()
        );
    }

    public Long getLocationByCategoryLocationID(Long id) throws ChangeSetPersister.NotFoundException {
        return this.locationServiceV2.getLocationByCategoryLocationID(id);
    }


    public LocationV2 getLocationByArticleLocation(Long locationId) throws ChangeSetPersister.NotFoundException {
        return this.locationServiceV2.getLocationByArticleLocation(locationId);
    }

    public LocationV2 saveLocation(LocationV2 location){
        return this.locationServiceV2.saveLocation(location);
    }

}
