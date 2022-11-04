package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.lOrder;
import nl.Groep13.OrderHandler.repository.OrderRepository;
import nl.Groep13.OrderHandler.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Optional;
import static org.mockito.Mockito.when;


@WebMvcTest(OrderDAO.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private lOrder orderUnderTesting;

    @InjectMocks
    private OrderService orderServiceMock;

    @Mock
    private OrderRepository orderRepositoryMock;


    @Test
    public void should_returnCorrectData_from_OrderId_when_getOrderByIdIsCalled() throws Exception{
        //Arrange
        Long expectedId = 8L;
        int expectedArticlenumber = 2050;
        int expectedCustomerId = 74;
        int expectedClaimed_by = 10;
        orderUnderTesting = new lOrder(expectedId, expectedArticlenumber, expectedCustomerId, expectedClaimed_by);
        when(orderRepositoryMock.findById(expectedId)).thenReturn(Optional.of(orderUnderTesting));

        Optional<lOrder> result = orderServiceMock.getOrderById(expectedId);
        assertThat(result, is(orderUnderTesting));

    }
}
