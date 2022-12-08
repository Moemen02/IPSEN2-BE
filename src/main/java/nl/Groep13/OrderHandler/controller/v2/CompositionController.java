package nl.Groep13.OrderHandler.controller.v2;


import nl.Groep13.OrderHandler.DAO.v2.ColorDAO;
import nl.Groep13.OrderHandler.DAO.v2.CompositionDAO;
import nl.Groep13.OrderHandler.model.v2.Color;
import nl.Groep13.OrderHandler.model.v2.Composition;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/composition")
public class CompositionController {
    private final CompositionDAO compositionDAO;

    public CompositionController(CompositionDAO compositionDAO) {
        this.compositionDAO = compositionDAO;
    }

    @GetMapping
    public ResponseEntity<List<Composition>> getAllComposition() {
        return ResponseEntity.ok(
                this.compositionDAO.getAllComposition()
        );
    }

    @GetMapping
    public ResponseEntity<Composition> getCompositionById(Long id) {
        try {
            Composition composition = this.compositionDAO.getCompositionById(id);
            return new ResponseEntity<>(composition, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Composition> addComposition(@RequestBody Composition composition) {
        if (composition == null) {
            throw new NullPointerException("Composition is empty");
        } else {
            return ResponseEntity.ok(composition);
        }
    }
}
