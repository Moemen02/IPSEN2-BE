package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.JWTPayload;
import nl.Groep13.OrderHandler.model.UserRole;
import nl.Groep13.OrderHandler.model.lOrder;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.OrderRepository;
import nl.Groep13.OrderHandler.security.JWTUtil;
import nl.Groep13.OrderHandler.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

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
    private OrderService orderServiceMock;

    @Mock
    private OrderRepository orderRepositoryMock;


    @Test
    public void updatedValue_should_be_equal_to_expectedValue_when_orderUpdated() throws Exception{
        //Arrange
        int expectedValue = 50;
        int actualValue;
        Long orderIdToUpdate = 8L;
        HashMap<String, String> dataToUpdateOrder = new HashMap<>();
        dataToUpdateOrder.put("claimed_by", String.valueOf(expectedValue));
        String orderToJson = gson.toJson(dataToUpdateOrder);
        lOrder newOrder = gson.fromJson(orderToJson, lOrder.class);

        //Act
        MvcResult result = mockMvc.perform(put("/api/order/"+orderIdToUpdate).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOrder)))
                .andExpect(status().isOk())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        lOrder responseOrder = gson.fromJson(httpResponse, lOrder.class);
        actualValue = responseOrder.getClaimed_by();

        //Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getOrderById_should_return_expected_id() throws Exception{
        //Arrange
        Long expectedId = 13L;
        Long actualId;
        Long orderIdToGet = expectedId;

        //Act
        MvcResult result = mockMvc.perform(get("/api/order/"+orderIdToGet))
                .andExpect(status().isOk())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        lOrder responseOrder = gson.fromJson(httpResponse, lOrder.class);
        actualId = responseOrder.getId();

        //Assert
        assertEquals(actualId, expectedId);


    }
}
