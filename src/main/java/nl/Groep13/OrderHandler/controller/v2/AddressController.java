package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.Address;
import nl.Groep13.OrderHandler.service.V2.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/address")
public class AddressController {

    private final AddressService addressService;

    Gson gson = new Gson();

    @Autowired
    public AddressController(AddressService addressService, Gson gson) {
        this.addressService = addressService;
        this.gson = gson;
    }

    @GetMapping(value = "/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long ID){
        try{
            Optional<Address> address = this.addressService.getAddressById(ID);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                this.addressService.getAllAddresses()
        );
    }

    //TODO UPDATE FUNCTIE NOG SCHRIJVEN

}
