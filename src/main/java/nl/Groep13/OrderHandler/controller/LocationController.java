package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/location")
public class LocationController {

    private LocationService locationService;

    Gson gson = new Gson();

   @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }



    @GetMapping
    public ResponseEntity<List<Location>> getAllLocation(){
       return ResponseEntity.ok(
               this.locationService.getAllLocations()
       );
    }

    @GetMapping(value = "/{articlenumber}")
    @ResponseBody
    public Optional<Location> getLocationByArticlenumber(@PathVariable Long articlenumber) throws ChangeSetPersister.NotFoundException{
       return this.locationService.getLocationByArticlenumber(articlenumber);
    }

    @PutMapping(value = "/{articlenumber}")
    @ResponseBody
    public Optional<Location> updateLocation(@PathVariable Long articlenumber, @RequestBody Map<String, String> location) throws JsonMappingException, JsonProcessingException{
       String locationToJson = gson.toJson(location);
       Location newLocation = gson.fromJson(locationToJson, Location.class);

       return this.locationService.updateLocation(articlenumber, Optional.of(newLocation));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteLocation(@PathVariable final Long articlenumber){
       try {
           locationService.deleteLocation(articlenumber);
       } catch (ChangeSetPersister.NotFoundException e){
           return ResponseEntity.ok(false);
       }
       return ResponseEntity.ok(true);
   }

   @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location){
       if (location == null){
           throw new NullPointerException("Location is empty");
       } else {
           locationService.addLocation(location);
           return ResponseEntity.ok(location);
       }
   }
}
