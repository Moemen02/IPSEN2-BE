package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import nl.Groep13.OrderHandler.model.v2.WasteOrder;
import nl.Groep13.OrderHandler.service.V2.WasteOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WasteOrderController.class)
class WasteOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteOrderService mockWasteOrderService;

    @Test
    void testGetWasteOrderById() throws Exception {
        // Setup
        // Configure WasteOrderService.getWasteOrderById(...).
        final ArticleV2 waste = new ArticleV2();
        waste.setId(0L);
        final ArticleData Waste_dataID = new ArticleData();
        Waste_dataID.setId(0L);
        Waste_dataID.setSupplier("supplier");
        Waste_dataID.setProductgroup("productgroup");
        Waste_dataID.setEancode("eancode");
        Waste_dataID.setColor("color");
        Waste_dataID.setPatternLength(0.0f);
        Waste_dataID.setPatternWidth(0.0f);
        Waste_dataID.setComposition("composition");
        Waste_dataID.setStockRL(false);
        waste.setArticle_dataID(Waste_dataID);
        final ArticleDescription Waste_descriptionID = new ArticleDescription();
        Waste_descriptionID.setId(0L);
        Waste_descriptionID.setArticlenumber("articlenumber");
        Waste_descriptionID.setDescription("description");
        waste.setArticle_descriptionID(Waste_descriptionID);
        final Optional<WasteOrder> wasteOrder = Optional.of(new WasteOrder(0L, waste, 0L, false));
        when(mockWasteOrderService.getWasteOrderById(0L)).thenReturn(wasteOrder);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order/{ID}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetWasteOrderById_WasteOrderServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockWasteOrderService.getWasteOrderById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order/{ID}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testGetWasteOrderById_WasteOrderServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockWasteOrderService.getWasteOrderById(0L)).thenThrow(ChangeSetPersister.NotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order/{ID}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllWasteOrders() throws Exception {
        // Setup
        // Configure WasteOrderService.getAllWasteOrders(...).
        final ArticleV2 waste = new ArticleV2();
        waste.setId(0L);
        final ArticleData Waste_dataID = new ArticleData();
        Waste_dataID.setId(0L);
        Waste_dataID.setSupplier("supplier");
        Waste_dataID.setProductgroup("productgroup");
        Waste_dataID.setEancode("eancode");
        Waste_dataID.setColor("color");
        Waste_dataID.setPatternLength(0.0f);
        Waste_dataID.setPatternWidth(0.0f);
        Waste_dataID.setComposition("composition");
        Waste_dataID.setStockRL(false);
        waste.setArticle_dataID(Waste_dataID);
        final ArticleDescription Waste_descriptionID = new ArticleDescription();
        Waste_descriptionID.setId(0L);
        Waste_descriptionID.setArticlenumber("articlenumber");
        Waste_descriptionID.setDescription("description");
        waste.setArticle_descriptionID(Waste_descriptionID);
        final List<WasteOrder> wasteOrders = List.of(new WasteOrder(0L, waste, 0L, false));
        when(mockWasteOrderService.getAllWasteOrders()).thenReturn(wasteOrders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllWasteOrders_WasteOrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockWasteOrderService.getAllWasteOrders()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetAllWasteOrders_WasteOrderServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockWasteOrderService.getAllWasteOrders()).thenThrow(ChangeSetPersister.NotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v2/waste_order")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
