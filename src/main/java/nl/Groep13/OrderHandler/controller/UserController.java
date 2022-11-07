package nl.Groep13.OrderHandler.controller;

import lombok.Getter;
import lombok.Setter;
import nl.Groep13.OrderHandler.DAO.UserDAO;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.User;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    public static final String SERVER_GOT_ERROR = "Er is iets fout gegaan op de server, probeer het later opnieuw";
    public static final String NEW_USER_MADE = "Nieuwe gebruiker aangemaakt";
    public static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    public static final String LOGGIN_IN_SUCCESS = "Inloggen was succesful";
    public static final String INVALID_PASSWORD = "Ongeldige inloggegevens";
    public static final String USER_NOT_FOUND = "Gebruiker niet gevonden";
    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authManager;

    /**
     * Register new user, if email already in database user will not be created and USER_ALREADY_EXISTS will be send back
     *
     * @param registerRequest - contains email, name, password and role
     * @return - JWT payload and success message
     */
    @PostMapping("/register")
    public JWTPayload registerHandler(@RequestBody RegisterRequest registerRequest){
        try {
            String token = userService.register(registerRequest);
            if (token.contains(USER_ALREADY_EXISTS)) return new JWTPayload("", USER_ALREADY_EXISTS, false);
            return new JWTPayload(token, NEW_USER_MADE, true);
        } catch (AuthenticationException e) {
            return new JWTPayload("", SERVER_GOT_ERROR, false);
        }
    }

    /**
     * End point for user login.
     * If user credentials are invalid error message INVALID_PASSWORD will be returned
     * @param body - email and password
     * @return JWT payload and success message
     */
    @PostMapping("/login")
    public JWTPayload loginHandler(@RequestBody LoginRequest body){
        try {
            String token = userService.login(body, authManager);
            return new JWTPayload(token, LOGGIN_IN_SUCCESS, true);
        }catch (AuthenticationException authExc){
            return new JWTPayload("", INVALID_PASSWORD, false);
        }
    }

    /**
     * get user name by given id
     * @param id - id of user from database
     * @return - name of user
     */
    @GetMapping("/user/{id}")
    @ResponseBody
    public String getUserByID(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) return user.get().getName();
        return USER_NOT_FOUND;
    }
}