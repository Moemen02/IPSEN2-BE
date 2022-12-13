package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.UsageDAO;
import nl.Groep13.OrderHandler.model.v2.Usage;
import nl.Groep13.OrderHandler.repository.v2.UsageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UsageControllerTest {
    @Autowired
    private UsageRepository usageRepository;

    private UsageController usageController;

    @Mock
    private UsageDAO usageDAO;

    @BeforeEach
    public void initialize() {
        usageDAO = new UsageDAO(usageRepository);
        usageController = new UsageController(usageDAO);
    }

    @Test
    public void Should_Retrieve_UsageList() {
        //Arrange
        List<Usage> usageList;

        //Act
        usageList = usageController.getAllUsages().getBody();

        //Assert
        assert usageList != null;
    }

    @Test
    public void Should_Retrieve_Id_One_WasteData() {
        //Arrange
        Usage usage;

        //Act
        usage = usageController.getUsageById(1L).getBody();

        //Assert
        assert usage != null;
    }

    @Test
    public void ShouldNot_Retrieve_Id_1Mil_Usage() {
        //Arrange
        Long OneMil = 1000000L;

        //Act
        ResponseEntity checkStatus = new ResponseEntity<Usage>(usageController.getUsageById(OneMil).getStatusCode());

        //Assert
        assertThat(checkStatus.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
