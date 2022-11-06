package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.DAO.ArticleDAO;
import nl.Groep13.OrderHandler.DAO.ArticleDetailDAO;
import nl.Groep13.OrderHandler.DAO.ArticlePriceDAO;
import nl.Groep13.OrderHandler.model.*;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.ArticleDetailRepository;
import nl.Groep13.OrderHandler.repository.ArticlePriceRepository;
import nl.Groep13.OrderHandler.repository.ArticleRepository;
import nl.Groep13.OrderHandler.security.JWTUtil;
import nl.Groep13.OrderHandler.service.ArticleDetailService;
import nl.Groep13.OrderHandler.service.ArticlePriceService;
import nl.Groep13.OrderHandler.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
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

    private ArticleController articleController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Autowired
    private ArticlePriceRepository articlePriceRepository;

    @Mock private ArticleService articleService;
    @Mock private ArticleDetailService articleDetailService;
    @Mock private ArticlePriceService articlePriceService;
    @Mock private ArticleDAO articleDAO;
    @Mock private ArticleDetailDAO articleDetailDAO;
    @Mock private ArticlePriceDAO articlePriceDAO;

    @BeforeEach
    public void initialize() {
        articleDAO = new ArticleDAO(articleRepository);
        articleDetailDAO = new ArticleDetailDAO(articleDetailRepository);
        articlePriceDAO = new ArticlePriceDAO(articlePriceRepository);
        articleService = new ArticleService(articleDAO);
        articleDetailService = new ArticleDetailService(articleDetailDAO);
        articlePriceService = new ArticlePriceService(articlePriceDAO);
        articleController = new ArticleController(articleService, articleDetailService, articlePriceService);
    }



    @Test
    public void Should_Retrieve_GivenArticlePrice_On_FilledArticlePrice(){
        //Arrange
        ArticlePrice articlePrice = new ArticlePrice(3605L, "Metal", 349, 485, 45, 30.37f, 373.00f, "ThisIsFake");

        //Act
        ArticlePrice result = articleController.addArticlePrice(articlePrice).getBody();

        //Assert
        assertThat(articlePrice, is(result));
    }

    @Test
    public void Should_Retrieve_NullPointerException_On_EmptyArticlePrice(){
        //Arrange
        ArticlePrice emptyArticlePrice = null;

        //Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> articleController.addArticlePrice(emptyArticlePrice));

        //Assert
        assertThat(thrown.getMessage(), is("ArticlePrice is empty"));
    }

    @Test
    public void Should_Retrieve_GivenArticleDetail_On_FilledArticleDetail(){
        //Arrange
        ArticleDetail articleDetail = new ArticleDetail("TESTING1", "Nepstoffen", "Holland Haag LE");

        //Act
        ArticleDetail result = articleController.addArticleDetail(articleDetail).getBody();

        //Assert
        assertThat(articleDetail, is(result));
    }

    @Test
    public void Should_Retrieve_NullPointerException_On_EmptyArticleDetail(){
        //Arrange
        ArticleDetail emptyArticleDetail = null;

        //Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> articleController.addArticleDetail(emptyArticleDetail));

        //Assert
        assertThat(thrown.getMessage(), is("ArticleDetail is empty"));
    }

    @Test
    public void Should_Retrieve_GivenArticle_On_FilledArticle() {
        //Arrange
        ArticleDetail articleDetail = new ArticleDetail("TESTING1", "Nepstoffen", "Holland Haag LE");
        ArticlePrice articlePrice = new ArticlePrice(3605L, "Metal", 349, 485, 45, 30.37f, 373.00f, "ThisIsFake");
        Article article = new Article(3605L, articlePrice.getId(), articleDetail.getEancode(), "GREY", "Solid", "Wqlcd", "100% Steel");
        articleController.addArticlePrice(articlePrice);
        articleController.addArticleDetail(articleDetail);

        //Act
        Article result = articleController.addArticle(article).getBody();

        //Assert
        assertThat(article, is(result));
    }

    @Test
    public void Should_Retrieve_NullPointerException_On_EmptyArticle() {
        //Arrange
        Article emptyArticle = null;

        //Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> articleController.addArticle(emptyArticle));

        //Assert
        assertThat(thrown.getMessage(), is("Article is empty"));
    }

    public String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = new Gson().toJson(loginRequest);
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);
        return payload.getJwtToken();
    }

    /**
     * made by momeen
     * @throws Exception
     */

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
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        Article newArticle = gson.fromJson(httpResponse, Article.class);
        actualValue = newArticle.getArticleId();


        //Assert
        assertEquals(ExpectedValue, actualValue);

    }

    @Test
    public void Should_ReturnNotFoundResponse_WhenArticleIdDoesNotExist() throws Exception {
        //Arrange
        String Token = getToken();
        Long articleIdToGet = 132343L;
        int ExpectedValue = 404;
        int httpResponse;
        int actualValue;


        //Act
        MvcResult result = (MvcResult) mockMvc.perform(
                        MockMvcRequestBuilders.get(path+articleIdToGet)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("authorization", "Bearer " + Token))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
        httpResponse = result.getResponse().getStatus();
        actualValue = httpResponse;

        //Assert
        assertEquals(ExpectedValue, actualValue);

    }

}
