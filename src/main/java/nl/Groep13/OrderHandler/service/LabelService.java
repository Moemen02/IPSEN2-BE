package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.LabelDAO;
import nl.Groep13.OrderHandler.model.Label;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class LabelService {

    private final LabelDAO labelDAO;

    public LabelService(LabelDAO labelDAO) {
        this.labelDAO = labelDAO;
    }


    public Optional<Label> getLabel(Long id) throws ChangeSetPersister.NotFoundException {
        return labelDAO.getLabel(id);
    }

    public Label addLabel(final Label label){
        return this.labelDAO.addLabel(label);
    }

}
