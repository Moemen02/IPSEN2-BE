package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
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

    public Optional<CategoryLocation> getCategoryLocationByLocation(Long id){
        return categoryLocationRepository.getCategoryLocationByLocation(id);
    }

}
