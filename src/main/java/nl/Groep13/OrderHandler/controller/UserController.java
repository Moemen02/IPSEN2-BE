package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authManager;


    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody RegisterRequest registerRequest){
        try {
            String token = userService.register(registerRequest);
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException e) {
            return Collections.singletonMap("jwtToken", "");
        }
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginRequest body){
        try {
            String token = userService.login(body, authManager);
            return Collections.singletonMap("jwtToken", token);
        }catch (AuthenticationException authExc){
            return Collections.singletonMap("jwtToken", "");
        }
    }
}