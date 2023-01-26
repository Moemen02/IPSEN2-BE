package nl.Groep13.OrderHandler.controller.v2;


import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class RequirementTest {

    private Gson gson = new Gson();
    String httpResponse;


    @BeforeEach
    public void initHttpTest(){
        this.httpResponse = new String();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRequirementById_should_return_expected_id() throws Exception{
        //Arrange
        Long expectedId = 9L;
        Long actualId;
        Long requirementIdToGet = expectedId;

        //Act
        MvcResult result = mockMvc.perform(get("/api/v2/requirement/"+requirementIdToGet))
                .andExpect(status().isOk())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();
        Requirement responseAddress = gson.fromJson(httpResponse, Requirement.class);
        actualId = responseAddress.getId();

        //Assert
        assertEquals(actualId, expectedId);


    }

}
