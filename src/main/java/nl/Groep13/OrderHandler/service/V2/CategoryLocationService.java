package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.CategoryLocationDAO;
import nl.Groep13.OrderHandler.model.v2.CategoryLocation;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryLocationService {

    private final CategoryLocationDAO categoryLocationDAO;

    public CategoryLocationService(CategoryLocationDAO categoryLocationDAO) {
        this.categoryLocationDAO = categoryLocationDAO;
    }

    public List<CategoryLocation> getAllCategoryLocations() throws ChangeSetPersister.NotFoundException{
        return this.categoryLocationDAO.getAllCategoryLocations();
    }

    public Optional<CategoryLocation> getCategoryLocationById(Long ID) throws ChangeSetPersister.NotFoundException{
        return this.categoryLocationDAO.getCategoryLocationById(ID);
    }

    public Optional<CategoryLocation> getCategoryLocationByLocation(Long id){
        return this.categoryLocationDAO.getCategoryLocationByLocation(id);
    }

}
