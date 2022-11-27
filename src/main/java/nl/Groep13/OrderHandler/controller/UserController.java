package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.User;
import nl.Groep13.OrderHandler.record.ChangePassword;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final String SERVER_GOT_ERROR = "Er is iets fout gegaan op de server, probeer het later opnieuw";
    private static final String NEW_USER_MADE = "Nieuwe gebruiker aangemaakt";
    private static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    private static final String LOGGIN_IN_SUCCESS = "Inloggen was succesful";
    private static final String INVALID_PASSWORD = "Ongeldige inloggegevens";
    private static final String CHANGED_PASSWORD_SUCCESS = "Uw nieuwe wachtwoord is opgeslagen";
    private static final String CHANGED_PASSWORD_FALIED = "Er is iets fout gegaan bij het updaten van Uw nieuwe wachtwoord, probeer het later opnieuw";
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

    @PostMapping("/changepassword")
    public JWTPayload changePassword(@RequestBody ChangePassword body){
        try {
            String token = userService.changePassword(body);
            return new JWTPayload(token, CHANGED_PASSWORD_SUCCESS, true);
        }catch (AuthenticationException authExc){
            return new JWTPayload("", CHANGED_PASSWORD_FALIED, false);
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
        return "";
    }
}