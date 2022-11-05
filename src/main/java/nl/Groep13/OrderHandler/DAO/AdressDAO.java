package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Adress;
import nl.Groep13.OrderHandler.model.Label;
import nl.Groep13.OrderHandler.repository.AdressRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdressDAO {
    private final AdressRepository adressRepository;

    public AdressDAO(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Optional<Adress> getAdress(final Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isPresent()) {
            return adress;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
