package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.AdressDAO;
import nl.Groep13.OrderHandler.model.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdressService {

    private final AdressDAO adressDAO;

    public AdressService(AdressDAO adressDAO) {
        this.adressDAO = adressDAO;
    }
    public Optional<Adress> getAdress(Long id) throws ChangeSetPersister.NotFoundException {
        return adressDAO.getAdress(id);
    }
}
