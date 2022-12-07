package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.model.Adress;
import nl.Groep13.OrderHandler.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/adress")
public class AdressController {
    private AdressService adressService;

    @Autowired
    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Adress> getAdress(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return adressService.getAdress(id);
    }


}
