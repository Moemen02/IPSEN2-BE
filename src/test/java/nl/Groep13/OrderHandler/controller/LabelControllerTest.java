package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.Label;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.security.JWTUtil;
import nl.Groep13.OrderHandler.service.MakeExcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LabelControllerTest {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    private final String name = "admin";
    private final String email = "admin@admin.com";
    private final UserRole role = UserRole.ADMIN;
    private final String password = "adminpass";
    private final String path = "/api/label/";
    private Gson gson = new Gson();

    public String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = new Gson().toJson(loginRequest);
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);
        return payload.getJwtToken();
    }

    @Test
    public void ShouldReturnLabelWhereOrderCodeIsSameAsGivenParameter() throws Exception {
        String Token = getToken();
        Long labelIdToGet = 1L;
        String expectedValue = "217123";
        String httpResponse;
        String actualValue;

        MvcResult result = (MvcResult) mockMvc.perform(
                        MockMvcRequestBuilders.get(path+labelIdToGet)
                                .header("authorization", "Bearer " + Token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        Map jsonMap = gson.fromJson(httpResponse, Map.class);
        actualValue = (String) jsonMap.get("orderCode");



        //Assert
        assertEquals(expectedValue, actualValue);
    }

}
