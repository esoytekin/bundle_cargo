package com.bundle.cargo.service.impl;

import com.bundle.cargo.adapter.ShippingResponseAdapter;
import com.bundle.cargo.client.SalesClient;
import com.bundle.cargo.model.Product;
import com.bundle.cargo.model.Sale;
import com.bundle.cargo.model.Shipping;
import com.bundle.cargo.service.ShippingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponse;

@Service
@RequiredArgsConstructor
public class ShippingStatusServiceImpl implements ShippingStatusService {

    private final SalesClient salesClient;

    @Override
    public ShippingStatusResponse getShippingStatusBySale(long saleId) {

        Sale sale = salesClient.findSaleById (saleId);
        Product product = salesClient.findProductById (sale.getProductId ());
        Shipping shipping = salesClient.findShippingBySaleId (saleId);

        return new ShippingResponseAdapter (shipping, product, sale);

    }
}
