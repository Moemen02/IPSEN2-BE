package nl.Groep13.OrderHandler.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.Category;
import nl.Groep13.OrderHandler.model.v2.Composition;
import nl.Groep13.OrderHandler.model.v2.LocationV2;
import nl.Groep13.OrderHandler.record.CompositionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    private Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper();
    String httpResponse;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void initHttpTest(){
        this.httpResponse = new String();
    }


    @Test
    public void get_All_Waste_Colors() throws Exception{
        //Arrange

        //Act
        MvcResult result = mockMvc.perform(get("/api/v2/category-waste/colors"))
                .andExpect(status().isOk())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();

        String[] colorList = gson.fromJson(httpResponse, String[].class);

        //Assert
        assertThat(colorList).isNotEmpty();
        assertThat(colorList.length).isGreaterThan(0);

    }

    @Test
    public void get_List_Based_on_composition() throws Exception{
        //Arrange
        List<Composition> compList = new ArrayList<>();
        Composition comp = new Composition(100, "CO", "SameThan");
        compList.add(comp);
        CompositionRequest CR = new CompositionRequest("100% katoen", 1, compList, 3);
        String json = gson.toJson(CR);

        //Act
        MvcResult result = mockMvc.perform(post("/api/v2/category-waste/composition")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andReturn();
        httpResponse = result.getResponse().getContentAsString();

        Category[] results = gson.fromJson(httpResponse, Category[].class);


        //Assert
        assertThat(results).isNotEmpty();
        assertThat(results[0].getComposition()).isEqualTo("100% CO");

    }
}
