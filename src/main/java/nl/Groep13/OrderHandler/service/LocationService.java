package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.LocationDao;
import nl.Groep13.OrderHandler.model.Location;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    LocationDao locationDao;

    public LocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public Optional<Location> GetLocationById(Long id){
        return locationDao.getLocationById(id);
    }
}
