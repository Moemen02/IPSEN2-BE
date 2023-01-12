package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDescriptionDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.repository.v2.ArticleDescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class WasteDescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleDescriptionRepository wasteDescriptionRepository;

    private WasteDescriptionController wasteDescriptionController;

    @Mock
    private WasteDescriptionDAO wasteDescriptionDAO;

    @BeforeEach
    public void initialize() {
        wasteDescriptionDAO = new WasteDescriptionDAO(wasteDescriptionRepository);
        wasteDescriptionController = new WasteDescriptionController(wasteDescriptionDAO);
    }

    @Test
    public void Should_Retrieve_WasteDescriptionList() {
        //Arrange
        List<ArticleDescription> wasteDescriptionList;

        //Act
        wasteDescriptionList = wasteDescriptionController.getAllWasteDescription().getBody();

        //Assert
        assert wasteDescriptionList != null;
    }

    @Test
    public void Should_Retrieve_Id_One_WasteData() {
        //Arrange
        ArticleDescription wasteDescription;

        //Act
        wasteDescription = wasteDescriptionController.getWasteDescriptionById(1L).getBody();

        //Assert
        assert wasteDescription != null;
    }

    @Test
    public void ShouldNot_Retrieve_Id_1Mil_WasteDescription() {
        //Arrange
        Long OneMil = 1000000L;

        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleDescription>(wasteDescriptionController.getWasteDescriptionById(OneMil).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void Should_Retrieve_GivenWasteData_On_FilledWasteData() {
        //Arrange
        ArticleDescription wasteDescription = new ArticleDescription(5000L, "Testing", "ForTesting", 50, "FakeStuff", "Patternless", "wQ3nc", 500, false, 0);
        ArticleDescription checkIfPresentBeforeTest = wasteDescriptionController.getWasteDescriptionById(wasteDescription.getId()).getBody();
        if (checkIfPresentBeforeTest != null) {
            wasteDescriptionController.deleteWasteDescription(5000L);
        }

        //Act
        ArticleDescription result = wasteDescriptionController.addWasteDescription(wasteDescription).getBody();

        //Assert
        assertThat(wasteDescription, is(result));
    }

    @Test
    public void Should_Delete_And_Notify_If_It_Existed() {
        //Arrange
        ArticleDescription checkIfNotPresentBeforeTest = wasteDescriptionController.getWasteDescriptionById(7000L).getBody();
        if (checkIfNotPresentBeforeTest != null) {
            wasteDescriptionController.deleteWasteDescription(7000L);
        }

        //Act
        ResponseEntity<Boolean> supposedToBeFalse = wasteDescriptionController.deleteWasteDescription(7000L);

        //Assert
        assertEquals(supposedToBeFalse.getBody(), false);
    }

    @Test
    public void Should_Be_Unable_To_Update_OneMil_Id() throws ChangeSetPersister.NotFoundException {
        Long OneMil = 1000000L;
        ArticleDescription wasteDescription = new ArticleDescription(5000L, "Testing", "ForTesting", 50, "FakeStuff", "Patternless", "wQ3nc", 500, false, 0);
        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleDescription>(wasteDescriptionController.updateWasteDescription(OneMil, wasteDescription).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
