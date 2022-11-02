package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.controller.*;
import nl.Groep13.OrderHandler.model.*;
import nl.Groep13.OrderHandler.repository.LabelRepository;
import nl.Groep13.OrderHandler.service.LabelService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;

@Component
public class LabelDAO {
    private final LabelRepository labelRepository;

    public LabelDAO(LabelRepository labelRepository) {this.labelRepository = labelRepository;}

    public Optional<Label> getLabel(final Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Label> label = labelRepository.findById(id);
        if (label.isPresent()) {
            return label;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Label addLabel(final Label label) {
        return this.labelRepository.save(label);
    }
}
