package nl.Groep13.OrderHandler;

import nl.Groep13.OrderHandler.utils.Pager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class PagerTest {

    @Test
    void Should_return_list_of_25_even_if_List_is_longer() {
        // Arrange
        int[] fullList = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        List<Integer> list = Arrays.stream(fullList).boxed().toList();


        //Act
        List<Integer> response1 = Pager.getRequestedItems(list, 0);
        List<Integer> response2 = Pager.getRequestedItems(list, 1);

        // Assert
        assertThat(response1.size()).isEqualTo(25);
        assertThat(response2.size()).isEqualTo(5);
    }

}
