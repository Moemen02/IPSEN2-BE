package nl.Groep13.OrderHandler.record;

public record ChangePassword(String email, String newPassword, String oldPassword) {
}
