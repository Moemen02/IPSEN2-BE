package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Color;
import nl.Groep13.OrderHandler.model.v2.Composition;
import nl.Groep13.OrderHandler.repository.v2.CompositionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompositionDAO {
    private final CompositionRepository compositionRepository;

    public CompositionDAO(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    public List<Composition> getAllComposition() {
        return compositionRepository.findAll();
    }

    public Composition addComposition(final Composition composition) {
        return this.compositionRepository.save(composition);
    }

    public Composition getCompositionById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Composition> composition = compositionRepository.findById(id);
        if (composition.isPresent()) {
            return composition.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
