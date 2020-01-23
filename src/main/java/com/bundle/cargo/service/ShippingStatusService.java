package com.bundle.cargo.service;

import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponse;

public interface ShippingStatusService {
    ShippingStatusResponse getShippingStatusBySale(long saleId);
}
