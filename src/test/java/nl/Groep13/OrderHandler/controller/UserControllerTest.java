package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.security.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private String name = "Jesse van Vuuren";
    private String email = "jessevv10@gmail.com";
    private UserRole role = UserRole.ADMIN;
    private String password = "adminpass";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MockMvc mvc;


    @Test
    void Should_NotRegisterNewUser_When_UserAlreadyExists() throws Exception{
        // Arrage
        RegisterRequest registerRequest = new RegisterRequest(name, email, role, password);
        String json = new Gson().toJson(registerRequest);
        String token = jwtUtil.generateToken("admin@admin.com", UserRole.ADMIN, "admin");

        // Act
        MvcResult result = mvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .header("Authorization", "Bearer " + token)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);


        // Assert
        assertEquals(payload.getMessage(), "gebuiker bestaat al");

    }

    @Test
    void Should_GiveUserJWTToken_When_LoggingIn() throws Exception{
        // Arrange
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = new Gson().toJson(loginRequest);

        // Act

        MvcResult result = mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);


        // Assert
        assertEquals(payload.getMessage(), "Ongeldige inloggegevens");
    }
}