package nl.Groep13.OrderHandler.controller;

import lombok.AllArgsConstructor;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.service.ArticleService;
import nl.Groep13.OrderHandler.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/login")
public class UserController {

    private final UserService userService;


    @PostMapping
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }


}
