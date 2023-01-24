package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.LocationV2;
import nl.Groep13.OrderHandler.repository.v2.LocationRepositoryV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocationDAOV2 {

    private final LocationRepositoryV2 locationRepositoryV2;

    public LocationDAOV2(LocationRepositoryV2 locationRepositoryV2) {
        this.locationRepositoryV2 = locationRepositoryV2;
    }

    public List<LocationV2> getAllLocations(){
        return locationRepositoryV2.findAll();
    }

    public Optional<LocationV2> getLocationById(final Long ID) throws ChangeSetPersister.NotFoundException{
        Optional<LocationV2> locationV2 = locationRepositoryV2.findById(ID);
        if (locationV2.isPresent()) {
            return locationV2;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Long getLocationByCategoryLocationID(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<LocationV2> location = Optional.ofNullable(locationRepositoryV2.getLocationByCategoryLocationID(id));
        if(location.isPresent()){
            return location.get().getId();
        }
        throw new  ChangeSetPersister.NotFoundException();
    }

    public LocationV2 saveLocation(LocationV2 location){
        return locationRepositoryV2.save(location);
    }

//    public Optional<LocationV2> updateLocation(Long ID, Optional<LocationV2> locationV2) throws ChangeSetPersister.NotFoundException{
//        Optional<LocationV2> oldLocationById = locationRepositoryV2.findById(ID);
//        if (oldLocationById.isPresent()){
//            LocationV2 oldLocation = oldLocationById.get();
//            LocationV2 newLocation = locationV2.get();
//
//            newLocation.setID((newLocation.getID() == null) ? oldLocation.getID() : newLocation.getID());
//            newLocation.setCategory_locationID((newLocation.getCategory_locationID() == null) ? oldLocation.getCategory_locationID() : newLocation.getCategory_locationID());
//            newLocation.setComposition((newLocation.getComposition() == null) ? oldLocation.getComposition() : newLocation.getComposition());
//            newLocation.setRequirementID((oldLocation.getRequirementID() == null) ? oldLocation.getRequirementID() : newLocation.getRequirementID());
//
//            locationRepositoryV2.setLocationById(
//                    newLocation.getCategory_locationID(),
//                    newLocation.getComposition(),
//                    newLocation.getRequirementID(),
//                    newLocation.getID()
//            );
//            locationRepositoryV2.save(newLocation);
//            return locationV2;
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }
//

}
