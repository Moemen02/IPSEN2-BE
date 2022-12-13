package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.security.JWTUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private String name = "admin";
    private String adminEmail = "admin@admin.com";
    private UserRole role = UserRole.ADMIN;
    private String password = "adminpass";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    public String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(adminEmail, password);
        String json = new Gson().toJson(loginRequest);
        MvcResult result = mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);
        return payload.getJwtToken();
    }


    @Test
    void Should_NotRegisterNewUser_When_UserAlreadyExists() throws Exception{
        // Arrage
        RegisterRequest registerRequest = new RegisterRequest(name, adminEmail, role);
        String json = new Gson().toJson(registerRequest);
        String token = jwtUtil.generateToken(adminEmail, role, name, 1L, false);

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
        LoginRequest loginRequest = new LoginRequest(adminEmail, password);
        String json = new Gson().toJson(loginRequest);

        // Act
        MvcResult result = mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);


        // Assert
        assertEquals(payload.getMessage(), "Inloggen was succesful");
        assertNotEquals(payload.getJwtToken(), "");
    }

    @Test
    void Should_GiveUnauthorized_When_NoToken() throws Exception{
        // Arrange
        String newUser = "newUserEmail@gmain.com";
        RegisterRequest registerRequest = new RegisterRequest(name, newUser, role);
        String json = new Gson().toJson(registerRequest);

        // Act
        MvcResult result = mvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        // Assert
        assertEquals(result.getResponse().getErrorMessage(), "Unauthorized");
    }

    @Test
    void ShouldReturnNameWhereUserIdIsSameAsGivenParameter() throws Exception {
        String Token = getToken();
        Long userIdToGet = 1L;
        String expectedValue = "admin";
        String httpResponse;
        String  actualValue;


        MvcResult result = (MvcResult) mvc.perform(
                        MockMvcRequestBuilders.get("/api/auth/user/"+ userIdToGet)
                                .header("authorization", "Bearer " + Token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andDo(print())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        actualValue = httpResponse;

        Assertions.assertEquals(expectedValue, actualValue);



    }
}