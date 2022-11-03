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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    public static final String SERVER_GOT_ERROR = "Er is iets fout gegaan op de server, probeer het later opnieuw";
    public static final String NEW_USER_MADE = "Nieuwe gebruiker aangemaakt";
    public static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authManager;

    @PostMapping("/register")
    public JWTPayload registerHandler(@RequestBody RegisterRequest registerRequest){
        try {
            String token = userService.register(registerRequest);
            if (token.contains("gebuiker bestaat al")) return new JWTPayload("", "gebuiker bestaat al", false);
            return new JWTPayload(token, "Nieuwe gebruiker aangemaakt", true);
        } catch (AuthenticationException e) {
            return new JWTPayload("", "Er is iets fout gegaan op de server, probeer het later opnieuw", false);
        }
    }

    @PostMapping("/login")
    public JWTPayload loginHandler(@RequestBody LoginRequest body){
        try {
            String token = userService.login(body, authManager);
            return new JWTPayload(token, "Inloggen was succesful", true);
        }catch (AuthenticationException authExc){
            return new JWTPayload("", "Ongeldige inloggegevens", false);
        }
    }
}