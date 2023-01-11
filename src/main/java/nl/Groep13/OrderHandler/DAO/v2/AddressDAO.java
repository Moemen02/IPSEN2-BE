package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Address;

import nl.Groep13.OrderHandler.repository.v2.AddressRepository;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressDAO {
    private final AddressRepository addressRepository;

    public AddressDAO(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(final Long ID) throws ChangeSetPersister.NotFoundException{
        Optional<Address> address = addressRepository.findById(ID);
        if (address.isPresent()) {
            return address;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

//    public Optional<Address> updateAddress(Long ID, Optional<Address> address) throws ChangeSetPersister.NotFoundException{
//        Optional<Address> oldAddressById = addressRepository.findById(ID);
//        if (oldAddressById.isPresent()){
//            Address oldAddress = oldAddressById.get();
//            Address newAddress = address.get();
//
//            newAddress.setID((newAddress.getID() == null) ? oldAddress.getID() : newAddress.getID());
//            newAddress.setHousenumber((newAddress.getHousenumber() == 0) ? oldAddress.getHousenumber() : newAddress.getHousenumber());
//            newAddress.setStreetname((newAddress.getStreetname() == null) ? oldAddress.getStreetname() : newAddress.getStreetname());
//            newAddress.setPostal_code((oldAddress.getPostal_code() == null) ? oldAddress.getPostal_code() : newAddress.getPostal_code());
//
//            addressRepository.setAddressById(
//                    newAddress.getHousenumber(),
//                    newAddress.getPostal_code(),
//                    newAddress.getStreetname(),
//                    newAddress.getID()
//            );
//            addressRepository.save(newAddress);
//            return address;
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }

}
