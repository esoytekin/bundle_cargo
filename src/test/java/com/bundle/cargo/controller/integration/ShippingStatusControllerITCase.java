package com.bundle.cargo.controller.integration;

import com.bundle.cargo.adapter.ShippingResponseAdapter;
import com.bundle.cargo.controller.ShippingStatusController;
import com.bundle.cargo.service.impl.ShippingStatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = {ShippingStatusController.class, ShippingStatusServiceImpl.class})
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
public class ShippingStatusControllerITCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessfulOperation() throws Exception {
        MvcResult mvcResult = mockMvc.perform (MockMvcRequestBuilders.get ("/sale/3/shipping"))
                .andExpect (MockMvcResultMatchers.request ().asyncStarted ())
                .andReturn ();

        mockMvc.perform (MockMvcRequestBuilders.asyncDispatch (mvcResult))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.status").value (ShippingResponseAdapter.FAILED_OPERATION))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.product.name").value ("Generic Concrete Ball"))
                .andExpect (MockMvcResultMatchers.jsonPath ("$.sale.code").value ("0c784421-3cbb-4566-ab72-06628413758b"));
    }

    @Test
    public void testNotFound404() throws Exception {

        mockMvc.perform (MockMvcRequestBuilders.get ("/sale/1/shipping"))
                .andExpect (MockMvcResultMatchers.status().is4xxClientError ());

    }
}
