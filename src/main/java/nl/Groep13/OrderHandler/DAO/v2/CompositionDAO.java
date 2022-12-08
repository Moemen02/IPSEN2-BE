package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Composition;
import nl.Groep13.OrderHandler.repository.v2.CompositionRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
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

    public Composition updateComposition(Long id, Composition newComposition) throws ChangeSetPersister.NotFoundException {
        if (compositionRepository.findById(id).isPresent()) {
            compositionRepository.setComposition(newComposition.getCompositionName(), id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void deleteCompositionById( Long id) throws ChangeSetPersister.NotFoundException {
        if (compositionRepository.findById(id).isPresent()) {
            compositionRepository.deleteCompositionById(id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
