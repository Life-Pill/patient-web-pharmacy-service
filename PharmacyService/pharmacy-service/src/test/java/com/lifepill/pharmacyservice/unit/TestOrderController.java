package com.lifepill.pharmacyservice.unit;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lifepill.pharmacyservice.controller.OrderController;
import com.lifepill.pharmacyservice.model.Order;
import com.lifepill.pharmacyservice.service.OrderService;

@WebMvcTest(value = OrderController.class)
public class TestOrderController {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    Order mockOrder = new Order(
            1L,
            1L,
            1L,
            false,
            1.99,
            LocalDateTime.of(2024, 05, 12, 00, 01, 31),
            LocalDateTime.of(2024, 05, 12, 00, 01, 31));

    String exampleOrderJson = "{\"orderId\": 1, \"pharmacyId\": 1, \"customerId\": 1, \"orderStatus\": false, \"paymentAmount\": 1.99, \"createdOn\": \"2024-05-12T00:01:31\", \"updatedOn\": \"2024-05-12T00:01:31\"}";

    // testing the get Order by id functionality
    @Test
    public void retrieveOrder() throws Exception {

        Mockito.when(orderService.getOrder(Mockito.anyLong(), Mockito.anyLong())).thenReturn(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/1/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"orderId\": 1, \"pharmacyId\": 1, \"customerId\": 1, \"orderStatus\": false, \"paymentAmount\": 1.99, \"createdOn\": \"2024-05-12T00:01:31\", \"updatedOn\": \"2024-05-12T00:01:31\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
