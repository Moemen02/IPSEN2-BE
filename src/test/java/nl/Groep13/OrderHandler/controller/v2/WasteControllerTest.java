package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.record.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class WasteControllerTest {

    private final String email = "admin@admin.com";
    private final UserRole role = UserRole.ADMIN;
    private final String password = "adminpass";

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
    void testGetWaste_WasteInterfaceReturnsNoItems() throws Exception {
        // Setup
        when(mockWasteInterface.getWaste()).thenReturn(Collections.emptyList());
        String Token = getToken();

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste")
                        .header("authorization", "Bearer " + Token))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
