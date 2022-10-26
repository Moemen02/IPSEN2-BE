package nl.Groep13.OrderHandler.controller;


import nl.Groep13.OrderHandler.DAO.LocationDao;
import nl.Groep13.OrderHandler.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/location")
public class LocationController {

    private LocationDao locationDao;
    private Location location;

    @Autowired
    public LocationController(LocationDao locationDao){
        this.locationDao = locationDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations(){
        List<Location> locations = this.locationDao.getAllLocations();

        return locations;
    }
}
