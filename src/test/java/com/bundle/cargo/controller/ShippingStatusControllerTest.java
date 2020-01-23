package com.bundle.cargo.controller;

import com.bundle.cargo.adapter.ShippingResponseAdapter;
import com.bundle.cargo.client.SalesClient;
import com.bundle.cargo.model.Product;
import com.bundle.cargo.model.Sale;
import com.bundle.cargo.model.Shipping;
import com.bundle.cargo.service.impl.ShippingStatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest({ShippingStatusController.class, ShippingStatusServiceImpl.class})
class ShippingStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesClient salesClient;

    @Test
    void testController() throws Exception {

        Sale sale = Sale.builder ().id (1).productId (1).saleCode ("abc").build ();
        Product product = Product.builder ().id (1).name ("elma").build ();
        Shipping shipping = Shipping.builder ().saleId (1).status (false).build ();

        Mockito.when (salesClient.findSaleById (1)).thenReturn (sale);
        Mockito.when (salesClient.findProductById (1)).thenReturn (product);
        Mockito.when (salesClient.findShippingBySaleId (1)).thenReturn (shipping);

        MvcResult mvcResult = mockMvc.perform (MockMvcRequestBuilders.get ("/sale/1/shipping"))
                .andExpect (MockMvcResultMatchers.request ().asyncStarted ())
                .andReturn ();

        mockMvc.perform (MockMvcRequestBuilders.asyncDispatch (mvcResult))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.status").value (ShippingResponseAdapter.FAILED_OPERATION))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.product.name").value ("elma"))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.sale.code").value ("abc"));
    }
}