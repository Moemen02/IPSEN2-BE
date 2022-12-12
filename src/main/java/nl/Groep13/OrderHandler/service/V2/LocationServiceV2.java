package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.LocationDAOV2;
import nl.Groep13.OrderHandler.model.v2.LocationV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceV2 {

    private final LocationDAOV2 locationDAOV2;

    public LocationServiceV2(LocationDAOV2 locationDAOV2) {
        this.locationDAOV2 = locationDAOV2;
    }

    public List<LocationV2> getAllLocations() throws ChangeSetPersister.NotFoundException{
        return this.locationDAOV2.getAllLocations();
    }

    public Optional<LocationV2> getLocationById(Long ID) throws ChangeSetPersister.NotFoundException{
        return this.locationDAOV2.getLocationById(ID);
    }

    public Optional<LocationV2> updateLocation(Long ID, Optional<LocationV2> address) throws ChangeSetPersister.NotFoundException{
        return this.locationDAOV2.updateLocation(ID, address);
    }

}
