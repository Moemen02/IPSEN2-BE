package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ColorDAO;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.v2.Color;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/color")
public class ColorController {

    private final ColorDAO colorDAO;

    public ColorController(ColorDAO colorDAO) {
        this.colorDAO = colorDAO;
    }

    @GetMapping
    public ResponseEntity<List<Color>> getAllColors() {
        return ResponseEntity.ok(
                this.colorDAO.getAllColors()
        );
    }

    @GetMapping
    public ResponseEntity<Color> getColorById(Long id) {
        try {
            Color color = this.colorDAO.getColorById(id);
            return new ResponseEntity<>(color, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Color> addColor(@RequestBody Color color) {
        if (color == null) {
            throw new NullPointerException("Color is empty");
        } else {
            return ResponseEntity.ok(color);
        }
    }
}
