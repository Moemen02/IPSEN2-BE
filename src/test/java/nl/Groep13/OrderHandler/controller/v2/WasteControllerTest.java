package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.DAO.v2.*;
import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import nl.Groep13.OrderHandler.repository.v2.ArticleDataRepository;
import nl.Groep13.OrderHandler.repository.v2.ArticleDescriptionRepository;
import nl.Groep13.OrderHandler.repository.v2.ArticleRepositoryV2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WasteControllerTest {

    private final String email = "admin@admin.com";
    private final UserRole role = UserRole.ADMIN;
    private final String password = "adminpass";

    @Autowired
    private ArticleRepositoryV2 wasteRepository;
    @Autowired
    private ArticleDataRepository wasteDataRepository;
    @Autowired
    private ArticleDescriptionRepository wasteDescriptionRepository;
    @Autowired
    private UsageRepository usageRepository;

    private WasteDataDAO wasteDataDAO;
    private WasteDescriptionDAO wasteDescriptionDAO;
    private UsageDAO usageDAO;

    private ArticleDAOV2 wasteDAO;
    private ArticleControllerV2 wasteController;


    @BeforeAll
    public void setUp() {
        wasteDataDAO = new WasteDataDAO(wasteDataRepository);
        wasteDescriptionDAO = new WasteDescriptionDAO(wasteDescriptionRepository);
        usageDAO = new UsageDAO(usageRepository);
        wasteDAO = new ArticleDAOV2(wasteRepository, wasteDataDAO, wasteDescriptionDAO, usageDAO);
//        wasteController = new ArticleControllerV2(wasteDAO, articleInterface);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleInterface mockWasteInterface;

    public String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = new Gson().toJson(loginRequest);
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        JWTPayload payload = new Gson().fromJson(result.getResponse().getContentAsString(), JWTPayload.class);
        return payload.getJwtToken();
    }

    @Test
    void testGetWaste() throws Exception {
        // Setup
        // Configure WasteInterface.getWaste(...).
        String Token = getToken();
        final ArticleV2 waste = new ArticleV2();
//        waste.setId(0L);
//        waste.setWaste_dataID(0L);
//        waste.setWaste_descriptionID(0L);
//        waste.setUsageID(0L);
        final List<ArticleV2> wastes = List.of(waste);
//        when(mockWasteInterface.getWaste()).thenReturn(wastes);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste")
                        .header("authorization", "Bearer " + Token))
                .andReturn().getResponse();

        System.out.println(response);

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetWaste_WasteInterfaceReturnsItems() throws Exception {
        // Setup
//        when(mockWasteInterface.getWaste()).thenReturn(Collections.emptyList());
        String Token = getToken();

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/article")
                        .header("authorization", "Bearer " + Token))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("[]");
    }

    @Test
    void AddAndUpdateWasteValue() throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        //Arrange
        ArticleData fillerData = new ArticleData(null, "Filler", "ADK-1000 Test", "2398", 2.5f, 3f, "100% PL", false, "Holland Haag Test");
        ArticleDescription fillerDescription = new ArticleDescription(null, "ADK-1000 Test", "ForTesting", 50, "Nepstoffen", "Compiled", "wQlsd", 100, false, 0);
        Usage usage = new Usage(null, "AFVAL");
        ArticleV2 testWaste = new ArticleV2();
        testWaste.setArticle_dataID(fillerData);
        testWaste.setArticle_descriptionID(fillerDescription);
        testWaste.setUsageID(usage);

        //Act
        ArticleV2 waste = wasteController.addWaste(testWaste).getBody();
        ArticleData altWasteData = waste.getArticle_dataID();
        altWasteData.setSupplier("Tester");
        waste.setArticle_dataID(altWasteData);
        ArticleV2 checkableWaste = wasteController.updateWaste(waste.getId(), waste).getBody();

        //Assert
        assert checkableWaste != null;
        assertThat(altWasteData.getSupplier()).isEqualTo(checkableWaste.getArticle_dataID().getSupplier());
    }
}
