package com.bundle.cargo.controller;

import com.bundle.cargo.service.ShippingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponse;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class ShippingStatusController implements org.swagger.bundleapi.rest.v1.SaleApi {

    private final ShippingStatusService shippingStatusService;

    @Override
    public CompletableFuture <ResponseEntity <ShippingStatusResponse>> getShippingStatusBySale(Long saleId) {

        ShippingStatusResponse shippingStatusBySale = shippingStatusService.getShippingStatusBySale (saleId);
        return CompletableFuture.completedFuture (ResponseEntity.ok (shippingStatusBySale));

    }

}
