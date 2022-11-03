package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.LocationDao;
import nl.Groep13.OrderHandler.model.Location;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationDao locationDao;

    public LocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public List<Location> getAllLocations(){
        return this.locationDao.getAllLocations();
    }

    public Optional<Location> getLocationByArticlenumber(final Long articlenumber) throws ChangeSetPersister.NotFoundException{
        return this.locationDao.getLocationByArticlenumber(articlenumber);
    }

    public Optional<Location> updateLocation(Long articlenumber, final Optional<Location> location){
        return this.locationDao.updateLocation(articlenumber, location);
    }

    public void deleteLocation(final Long articlenumber) throws ChangeSetPersister.NotFoundException{
        this.locationDao.deleteLocation(articlenumber);
    }

    public Location addLocation(final Location location){
        return this.locationDao.addLocation(location);
    }
}
