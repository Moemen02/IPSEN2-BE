package nl.Groep13.OrderHandler.DAO;


import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.repository.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocationDao {

    private final LocationRepository locationRepository;

    public LocationDao(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations(){
        List<Location> locations= this.locationRepository.findAll();

        return locations;
    }

    public Optional<Location> getLocationById(Long id){
        return locationRepository.findById(id);
    }

}
