package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleDataDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.repository.v2.ArticleDataRepository;
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
public class ArticleDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleDataRepository wasteDataRepository;

    private ArticleDataController articleDataController;

    @Mock
    private ArticleDataDAO articleDataDAO;

    @BeforeEach
    public void initialize() {
        articleDataDAO = new ArticleDataDAO(wasteDataRepository);
        articleDataController = new ArticleDataController(articleDataDAO);
    }

    @Test
    public void Should_Retrieve_WasteDataList() {
        //Arrange
        List<ArticleData> wasteDataList;

        //Act
        wasteDataList = articleDataController.getAllWasteData().getBody();

        //Assert
        assert wasteDataList != null;
    }

    @Test
    public void Should_Retrieve_Id_One_WasteData() {
        //Arrange
        ArticleData wasteData;

        //Act
        wasteData = articleDataController.getWasteDataById(1L).getBody();

        //Assert
        assert wasteData != null;
    }

    @Test
    public void ShouldNot_Retrieve_Id_1Mil_WasteData() {
        //Arrange
        Long OneMil = 1000000L;

        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleData>(articleDataController.getWasteDataById(OneMil).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void Should_Retrieve_GivenWasteData_On_FilledWasteData() {
        //Arrange
        ArticleData wasteData = new ArticleData(5000L, "Tester", "Zorio-347", "Red", 5f, 0f, "100% PL", false, "Teststoffen");
        ArticleData checkIfPresentBeforeTest = articleDataController.getWasteDataById(wasteData.getId()).getBody();
        if (checkIfPresentBeforeTest != null) {
            articleDataController.deleteWasteData(5000L);
        }

        //Act
        ArticleData result = articleDataController.addWasteData(wasteData).getBody();

        //Assert
        assertThat(wasteData, is(result));
    }

    @Test
    public void Should_Delete_And_Notify_If_It_Existed() {
        //Arrange
        ArticleData checkIfNotPresentBeforeTest = articleDataController.getWasteDataById(7000L).getBody();
        if (checkIfNotPresentBeforeTest != null) {
            articleDataController.deleteWasteData(7000L);
        }

        //Act
        ResponseEntity<Boolean> supposedToBeFalse = articleDataController.deleteWasteData(7000L);

        //Assert
        assertEquals(supposedToBeFalse.getBody(), false);
    }

    @Test
    public void Should_Be_Unable_To_Update_OneMil_Id() throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        Long OneMil = 1000000L;
        ArticleData wasteData = new ArticleData(5000L, "Tester", "Zorio-347", "Red", 5f, 0f, "100% PL", false, "Teststoffen");

        //Act
        ResponseEntity checkStatus = new ResponseEntity<ArticleData>(articleDataController.updateWasteData(OneMil, wasteData).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
