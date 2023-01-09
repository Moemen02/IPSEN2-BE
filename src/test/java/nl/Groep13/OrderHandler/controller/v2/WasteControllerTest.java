package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.DAO.v2.UsageDAO;
import nl.Groep13.OrderHandler.DAO.v2.WasteDAO;
import nl.Groep13.OrderHandler.DAO.v2.WasteDataDAO;
import nl.Groep13.OrderHandler.DAO.v2.WasteDescriptionDAO;
import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteDescriptionRepository;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
    private WasteRepository wasteRepository;

    @Autowired
    private WasteDataRepository wasteDataRepository;

    @Autowired
    private WasteDescriptionRepository wasteDescriptionRepository;
    @Autowired
    private UsageRepository usageRepository;

    private WasteDataDAO wasteDataDAO;
    private WasteDescriptionDAO wasteDescriptionDAO;
    private UsageDAO usageDAO;

    private WasteDAO wasteDAO;
    private WasteController wasteController;


    @BeforeAll
    public void setUp() {
        wasteDataDAO = new WasteDataDAO(wasteDataRepository);
        wasteDescriptionDAO = new WasteDescriptionDAO(wasteDescriptionRepository);
        usageDAO = new UsageDAO(usageRepository);
        wasteDAO = new WasteDAO(wasteRepository, wasteDataDAO, wasteDescriptionDAO, usageDAO);
        wasteController = new WasteController(wasteDAO);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteInterface mockWasteInterface;

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
        final Waste waste = new Waste();
//        waste.setId(0L);
//        waste.setWaste_dataID(0L);
//        waste.setWaste_descriptionID(0L);
//        waste.setUsageID(0L);
        final List<Waste> wastes = List.of(waste);
        when(mockWasteInterface.getWaste()).thenReturn(wastes);

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
        when(mockWasteInterface.getWaste()).thenReturn(Collections.emptyList());
        String Token = getToken();

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste")
                        .header("authorization", "Bearer " + Token))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("[]");
    }

    @Test
    void AddAndUpdateWasteValue() throws ChangeSetPersister.NotFoundException {
        //Arrange
        WasteData fillerData = new WasteData(null, "Filler", "ADK-1000 Test", "2398", 2.5f, 3f, "100% PL", false, "Holland Haag Test");
        WasteDescription fillerDescription = new WasteDescription(null, "ADK-1000 Test", "ForTesting", 50, "Nepstoffen", "Compiled", "wQlsd", 100, false, 0);
        Usage usage = new Usage(null, "BEHOUD");
        Waste testWaste = new Waste();
        testWaste.setWaste_dataID(fillerData);
        testWaste.setWaste_descriptionID(fillerDescription);
        testWaste.setUsageID(usage);

        //Act
        Waste waste = wasteController.addWaste(testWaste).getBody();
        WasteData altWasteData = waste.getWaste_dataID();
        altWasteData.setSupplier("Tester");
        waste.setWaste_dataID(altWasteData);
        Waste checkableWaste = wasteController.updateWaste(waste.getId(), waste).getBody();

        //Assert
        assert checkableWaste != null;
        assertThat(altWasteData.getSupplier()).isEqualTo(checkableWaste.getWaste_dataID().getSupplier());
    }
}
