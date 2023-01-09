package nl.Groep13.OrderHandler.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.LocationV2;
import nl.Groep13.OrderHandler.repository.v2.LocationRepositoryV2;
import nl.Groep13.OrderHandler.service.V2.LocationServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LocationV2Test {

    private Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper();
    String httpResponse;

    @BeforeEach
    public void initHttpTest(){
        this.httpResponse = new String();
    }


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private LocationServiceV2 locationServiceV2;

    @Mock
    private LocationRepositoryV2 locationRepositoryV2;

    @Test
    public void getLocationById_should_return_expected_id() throws Exception{
        //Arrange
        Long expectedId = 9L;
        Long actualId;
        Long locationIdToGet = expectedId;

        //Act
        MvcResult result = mockMvc.perform(get("/api/v2/location/"+locationIdToGet))
                .andExpect(status().isOk())
                .andReturn();

        httpResponse = result.getResponse().getContentAsString();

        LocationV2 responseLocation = gson.fromJson(httpResponse, LocationV2.class);
        actualId = responseLocation.getId();

        //Assert
        assertEquals(expectedId ,actualId);


    }

}
