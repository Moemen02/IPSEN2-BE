package nl.Groep13.OrderHandler.DAO;



import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.repository.LocationRepository;

import org.springframework.data.crossstore.ChangeSetPersister;
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

    public Optional<Location> getLocationByArticlenumber(final Long articlenumber) throws ChangeSetPersister.NotFoundException{
        Optional<Location> location = this.locationRepository.findById(articlenumber);

        if (location.isPresent()){
            return location;
        }
        throw new ChangeSetPersister.NotFoundException();

    }

    public Optional<Location> updateLocation(Long articlenumber, Optional<Location> location) {
        Optional<Location> oldLocationById = locationRepository.findById(articlenumber);
        Location oldLocation = oldLocationById.get();
        Location newLocation = location.get();

        newLocation.setArticlenumber((newLocation.getArticlenumber() == null) ? oldLocation.getArticlenumber() : newLocation.getArticlenumber());
        newLocation.setType_storage((newLocation.getType_storage() == null) ? oldLocation.getType_storage() : newLocation.getType_storage());
        newLocation.setBranch((newLocation.getBranch() == null) ? oldLocation.getBranch() : newLocation.getBranch());

        locationRepository.setLocationInfoByArticleNumber(
                newLocation.getType_storage(),
                newLocation.getBranch(),
                newLocation.getArticlenumber()
        );

        locationRepository.save(newLocation);
        return location;
    }

    public void deleteLocation(final Long articlenumber) throws ChangeSetPersister.NotFoundException {
        if (locationRepository.findById(articlenumber).isPresent()) {
            locationRepository.deleteLocationByArticlenumber(articlenumber);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Location addLocation(final Location location) {
        location.setArticlenumber(null);
        return this.locationRepository.save(location);
    }

}
