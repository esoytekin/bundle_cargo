package com.bundle.cargo.adapter;

import com.bundle.cargo.model.Product;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponseProduct;

public class ProductResponseAdapter extends ShippingStatusResponseProduct {
    public ProductResponseAdapter(Product product) {
        setId (product.getId ());
        setName (product.getName ());
        setPrice (product.getPrice ());
    }
}
