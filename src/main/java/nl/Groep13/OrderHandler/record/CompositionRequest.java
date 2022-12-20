package nl.Groep13.OrderHandler.record;

import nl.Groep13.OrderHandler.model.v2.Composition;

import java.util.List;

public record CompositionRequest(String name, Integer color, List<Composition> conditions, int stockType) {
}
