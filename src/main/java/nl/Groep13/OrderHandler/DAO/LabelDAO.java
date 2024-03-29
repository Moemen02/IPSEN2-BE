package nl.Groep13.OrderHandler.DAO;


import nl.Groep13.OrderHandler.model.*;
import nl.Groep13.OrderHandler.repository.LabelRepository;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LabelDAO {
    private final LabelRepository labelRepository;

    public LabelDAO(LabelRepository labelRepository) {this.labelRepository = labelRepository;}

    public Optional<Label> getLabel(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Label> label = labelRepository.findByOrderid(id);
        if (label.isPresent()) {
            return label;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Label addLabel(final Label label) {
        return this.labelRepository.save(label);
    }
}
