package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.controller.v2.ArticleOrderController;
import nl.Groep13.OrderHandler.controller.v2.WasteLocationController;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
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

    public LocationV2 getLocationByArticleLocation(Long id) throws ChangeSetPersister.NotFoundException {
        return this.locationRepositoryV2.getLocationByArticleLocation(id);
    }

}
