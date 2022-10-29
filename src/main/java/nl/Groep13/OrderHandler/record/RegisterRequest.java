package nl.Groep13.OrderHandler.record;

import nl.Groep13.OrderHandler.model.UserRole;

public record RegisterRequest(String name, String email, UserRole role, String password) {
}
