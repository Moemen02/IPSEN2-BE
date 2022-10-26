package nl.Groep13.OrderHandler.service;

import lombok.AllArgsConstructor;
import nl.Groep13.OrderHandler.DAO.UserDAO;
import nl.Groep13.OrderHandler.model.User;
import nl.Groep13.OrderHandler.record.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;

    public String login(LoginRequest request) {
        Optional<User> user = userDAO.getUserByEmail(request.email());

        if (user.isPresent()) {
            return "Welkom " + user.get().getName();
        }

        return "No user found with email: " + request.email();
    }
}
