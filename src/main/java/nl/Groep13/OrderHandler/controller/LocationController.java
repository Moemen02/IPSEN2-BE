package nl.Groep13.OrderHandler.controller;


import nl.Groep13.OrderHandler.DAO.LocationDao;
import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/location")
public class LocationController {

    private LocationDao locationDao;
    private Location location;
    private LocationService locationService;
    public LocationController(){}

    @Autowired
    public LocationController(LocationDao locationDao, LocationService locationService){
        this.locationDao = locationDao;
        this.locationService = locationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations(){
        List<Location> locations = this.locationDao.getAllLocations();

        return locations;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Location> getLocationByID(@PathVariable Long id){
        return locationService.GetLocationById(id);
    }
}
