package com.bundle.cargo.adapter;

import com.bundle.cargo.model.Product;
import com.bundle.cargo.model.Sale;
import com.bundle.cargo.model.Shipping;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponse;

public class ShippingResponseAdapter extends ShippingStatusResponse {

    public static final String SUCCESSFUL_OPERATION = "Teslim Edildi";
    public static final String FAILED_OPERATION = "Teslim Edilmedi";
    public ShippingResponseAdapter(Shipping shipping, Product product, Sale sale) {

        SaleResponseAdapter saleResponseAdapter = new SaleResponseAdapter (sale);
        ProductResponseAdapter productResponseAdapter = new ProductResponseAdapter (product);

        String status = shipping.isStatus () ? SUCCESSFUL_OPERATION : FAILED_OPERATION;

        this.product(productResponseAdapter)
                .sale (saleResponseAdapter)
                .status (status);
    }
}
