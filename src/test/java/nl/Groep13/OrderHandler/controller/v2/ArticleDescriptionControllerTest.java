package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleDescriptionDAO;
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
public class ArticleDescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleDescriptionRepository wasteDescriptionRepository;

    private ArticleDescriptionController articleDescriptionController;

    @Mock
    private ArticleDescriptionDAO articleDescriptionDAO;

    @BeforeEach
    public void initialize() {
        articleDescriptionDAO = new ArticleDescriptionDAO(wasteDescriptionRepository);
        articleDescriptionController = new ArticleDescriptionController(articleDescriptionDAO);
    }

    @Test
    public void Should_Retrieve_WasteDescriptionList() {
        //Arrange
        List<ArticleDescription> wasteDescriptionList;

        //Act
        wasteDescriptionList = articleDescriptionController.getAllWasteDescription().getBody();

        //Assert
        assert wasteDescriptionList != null;
    }

    @Test
    public void Should_Retrieve_Id_One_WasteData() {
        //Arrange
        ArticleDescription wasteDescription;

        //Act
        wasteDescription = articleDescriptionController.getWasteDescriptionById(1L).getBody();

        //Assert
        assert wasteDescription != null;
    }

    @Test
    public void ShouldNot_Retrieve_Id_1Mil_WasteDescription() {
        //Arrange
        Long OneMil = 1000000L;

        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleDescription>(articleDescriptionController.getWasteDescriptionById(OneMil).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void Should_Retrieve_GivenWasteData_On_FilledWasteData() {
        //Arrange
        ArticleDescription wasteDescription = new ArticleDescription(5000L, "Testing", "ForTesting", 50, "FakeStuff", "Patternless", "wQ3nc", 500, false, 0);
        ArticleDescription checkIfPresentBeforeTest = articleDescriptionController.getWasteDescriptionById(wasteDescription.getId()).getBody();
        if (checkIfPresentBeforeTest != null) {
            articleDescriptionController.deleteWasteDescription(5000L);
        }

        //Act
        ArticleDescription result = articleDescriptionController.addWasteDescription(wasteDescription).getBody();

        //Assert
        assertThat(wasteDescription, is(result));
    }

    @Test
    public void Should_Delete_And_Notify_If_It_Existed() {
        //Arrange
        ArticleDescription checkIfNotPresentBeforeTest = articleDescriptionController.getWasteDescriptionById(7000L).getBody();
        if (checkIfNotPresentBeforeTest != null) {
            articleDescriptionController.deleteWasteDescription(7000L);
        }

        //Act
        ResponseEntity<Boolean> supposedToBeFalse = articleDescriptionController.deleteWasteDescription(7000L);

        //Assert
        assertEquals(supposedToBeFalse.getBody(), false);
    }

    @Test
    public void Should_Be_Unable_To_Update_OneMil_Id() throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Long OneMil = 1000000L;
        ArticleDescription wasteDescription = new ArticleDescription(5000L, "Testing", "ForTesting", 50, "FakeStuff", "Patternless", "wQ3nc", 500, false, 0);
        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleDescription>(articleDescriptionController.updateWasteDescription(OneMil, wasteDescription).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
