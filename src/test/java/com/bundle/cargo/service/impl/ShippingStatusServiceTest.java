package com.bundle.cargo.service.impl;

import com.bundle.cargo.adapter.ShippingResponseAdapter;
import com.bundle.cargo.client.SalesClient;
import com.bundle.cargo.model.Product;
import com.bundle.cargo.model.Sale;
import com.bundle.cargo.model.Shipping;
import com.bundle.cargo.service.ShippingStatusService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponse;

class ShippingStatusServiceTest {

    private static ShippingStatusService shippingStatusService;
    private static Shipping mockShipping;

    @BeforeAll
    static void setUp() {
        SalesClient salesClient = Mockito.mock (SalesClient.class);
        shippingStatusService = new ShippingStatusServiceImpl (salesClient);
        Sale mockSale = Sale.builder ()
                .id (1)
                .productId (1)
                .saleCode ("abcd")
                .build ();

        Product mockProduct = Product.builder ()
                .id (1)
                .name ("elma")
                .price (3.0)
                .image ("image")
                .build ();

        mockShipping = Shipping.builder ()
                .saleId (1)
                .status (true)
                .build ();

        Mockito.when (salesClient.findSaleById (1)).thenReturn (mockSale);
        Mockito.when (salesClient.findProductById (1)).thenReturn (mockProduct);
        Mockito.when (salesClient.findShippingBySaleId (1)).thenReturn (mockShipping);

    }

    @Test
    void testReturnSuccessIfShippingStatusTrue() {
        mockShipping.setStatus (true);
        ShippingStatusResponse shippingStatusBySale = shippingStatusService.getShippingStatusBySale (1);
        Assertions.assertAll(
                () -> Assertions.assertEquals (ShippingResponseAdapter.SUCCESSFUL_OPERATION, shippingStatusBySale.getStatus ()),
                () -> Assertions.assertEquals ("elma", shippingStatusBySale.getProduct ().getName ()),
                () -> Assertions.assertEquals (3.0, shippingStatusBySale.getProduct ().getPrice ()),
                () -> Assertions.assertEquals ("abcd", shippingStatusBySale.getSale ().getCode ())
        );
    }

    @Test
    void testReturnFailIfShippingStatusFalse(){

        mockShipping.setStatus (false);

        ShippingStatusResponse shippingStatusBySale = shippingStatusService.getShippingStatusBySale (1);
        Assertions.assertEquals (ShippingResponseAdapter.FAILED_OPERATION, shippingStatusBySale.getStatus ());

    }

}