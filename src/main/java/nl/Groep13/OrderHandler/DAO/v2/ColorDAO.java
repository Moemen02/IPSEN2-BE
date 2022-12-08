package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.Color;
import nl.Groep13.OrderHandler.repository.v2.ColorRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ColorDAO {
    private final ColorRepository colorRepository;

    public ColorDAO(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color addColor(final Color color) {
        return this.colorRepository.save(color);
    }

    public Color getColorById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return color.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
