package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.AddressDAO;

import nl.Groep13.OrderHandler.model.v2.Address;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressDAO addressDAO;

    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public List<Address> getAllAddresses() throws ChangeSetPersister.NotFoundException{
        return this.addressDAO.getAllAddresses();
    }

    public Optional<Address> getAddressById(Long ID) throws ChangeSetPersister.NotFoundException{
        return this.addressDAO.getAddressById(ID);
    }

//    public Optional<Address> updateAddress(Long ID, Optional<Address> address) throws ChangeSetPersister.NotFoundException{
//        return this.addressDAO.updateAddress(ID, address);
//    }
}
