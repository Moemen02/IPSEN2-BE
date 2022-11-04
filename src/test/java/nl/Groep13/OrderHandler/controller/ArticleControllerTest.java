package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.security.JWTUtil;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArticleControllerTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    private final String name = "admin";
    private final String email = "admin@admin.com";
    private final UserRole role = UserRole.ADMIN;
    private final String password = "adminpass";
    private final String path = "/api/article/";
    private Gson gson = new Gson();

    /**
     *
     * The getToken() is made by Jesse
     * This function is used to get a Token from a user
     * so you can do http calls
     */

    public String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = new Gson().toJson(loginRequest);
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);
        return payload.getJwtToken();
    }

    @Test
    public void Should_ReturnArticle_WhereArticleIdIsSameAsGivenParameter() throws Exception {
        //Arrange
        String Token = getToken();
        Long articleIdToGet = 1L;
        Long ExpectedValue = articleIdToGet;
        String httpResponse;
        Long actualValue;


        //Act
        MvcResult result = (MvcResult) mockMvc.perform(
                MockMvcRequestBuilders.get(path+articleIdToGet)
                .header("authorization", "Bearer " + Token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        Article newArticle = gson.fromJson(httpResponse, Article.class);
        actualValue = newArticle.getArticleId();


        //Assert
        assertEquals(ExpectedValue, actualValue);

    }

}
