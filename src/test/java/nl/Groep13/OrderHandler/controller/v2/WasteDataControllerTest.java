package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDataDAO;
import nl.Groep13.OrderHandler.controller.v2.WasteDataController;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.repository.v2.WasteDataRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class WasteDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WasteDataRepository wasteDataRepository;

    private WasteDataController wasteDataController;

    @Mock
    private WasteDataDAO wasteDataDAO;

    @BeforeEach
    public void initialize() {
        wasteDataDAO = new WasteDataDAO(wasteDataRepository);
        wasteDataController = new WasteDataController(wasteDataDAO);
    }

    @Test
    public void Should_Retrieve_WasteDataList() {
        //Arrange
        List<WasteData> wasteDataList;

        //Act
        wasteDataList = wasteDataController.getAllWasteData().getBody();

        //Assert
        assert wasteDataList != null;
    }

    @Test
    public void Should_Retrieve_Id_One_WasteData() {
        //Arrange
        WasteData wasteData;

        //Act
        wasteData = wasteDataController.getWasteDataById(1L).getBody();

        //Assert
        assert wasteData != null;
    }

    @Test
    public void ShouldNot_Retrieve_Id_1Mil_WasteData() {
        //Arrange
        Long OneMil = 1000000L;

        //Act
        ResponseEntity checkStatus = new ResponseEntity<WasteData>(wasteDataController.getWasteDataById(OneMil).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void Should_Retrieve_GivenWasteData_On_FilledWasteData() {
        //Arrange
        WasteData wasteData = new WasteData(5000L, "Tester", "Zorio-347", "Red", 5f, 0f, "100% PL", false, "Teststoffen");
        WasteData checkIfPresentBeforeTest = wasteDataController.getWasteDataById(wasteData.getId()).getBody();
        if (checkIfPresentBeforeTest != null) {
            wasteDataController.deleteWasteData(5000L);
        }

        //Act
        WasteData result = wasteDataController.addWasteData(wasteData).getBody();

        //Assert
        assertThat(wasteData, is(result));
    }

    @Test
    public void Should_Delete_And_Notify_If_It_Existed() {
        //Arrange
        WasteData checkIfNotPresentBeforeTest = wasteDataController.getWasteDataById(7000L).getBody();
        if (checkIfNotPresentBeforeTest != null) {
            wasteDataController.deleteWasteData(7000L);
        }

        //Act
        ResponseEntity<Boolean> supposedToBeFalse = wasteDataController.deleteWasteData(7000L);

        //Assert
        assertEquals(supposedToBeFalse.getBody(), false);
    }

    @Test
    public void Should_Be_Unable_To_Update_OneMil_Id() throws ChangeSetPersister.NotFoundException {
        Long OneMil = 1000000L;
        WasteData wasteData = new WasteData(5000L, "Tester", "Zorio-347", "Red", 5f, 0f, "100% PL", false, "Teststoffen");

        //Act
        ResponseEntity checkStatus = new ResponseEntity<WasteData>(wasteDataController.updateWasteData(OneMil, wasteData).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
