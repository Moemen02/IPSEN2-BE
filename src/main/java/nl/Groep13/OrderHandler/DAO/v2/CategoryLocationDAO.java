package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Address;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import nl.Groep13.OrderHandler.repository.v2.CategoryLocationRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryLocationDAO {

    private final CategoryLocationRepository categoryLocationRepository;

    public CategoryLocationDAO(CategoryLocationRepository categoryLocationRepository) {
        this.categoryLocationRepository = categoryLocationRepository;
    }

    public List<CategoryLocation> getAllCategoryLocations(){
        return categoryLocationRepository.findAll();
    }

    public Optional<CategoryLocation> getCategoryLocationById(final Long ID) throws ChangeSetPersister.NotFoundException{
        Optional<CategoryLocation> categoryLocation = categoryLocationRepository.findById(ID);
        if (categoryLocation.isPresent()) {
            return categoryLocation;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Optional<CategoryLocation> updateCategoryLocation(Long ID, Optional<CategoryLocation> categoryLocation) throws ChangeSetPersister.NotFoundException{
        Optional<CategoryLocation> oldCategoryLocationById = categoryLocationRepository.findById(ID);
        if (oldCategoryLocationById.isPresent()){
            CategoryLocation oldCategoryLocation = oldCategoryLocationById.get();
            CategoryLocation newCategoryLocation = categoryLocation.get();
            newCategoryLocation.setID((newCategoryLocation.getID() == null) ? oldCategoryLocation.getID() : newCategoryLocation.getID());
            newCategoryLocation.setLocation_type((newCategoryLocation.getLocation_type() == null) ? oldCategoryLocation.getLocation_type() : newCategoryLocation.getLocation_type());

            categoryLocationRepository.setCategorylocationById(
                    newCategoryLocation.getLocation_type(),
                    newCategoryLocation.getID()
            );
            categoryLocationRepository.save(newCategoryLocation);
            return categoryLocation;
        }
        throw new ChangeSetPersister.NotFoundException();
    }


}
