package com.bundle.cargo.adapter;

import com.bundle.cargo.model.Sale;
import org.swagger.bundleapi.rest.v1.model.ShippingStatusResponseSale;

public class SaleResponseAdapter extends ShippingStatusResponseSale{

    public SaleResponseAdapter(Sale sale) {
        this.code (sale.getSaleCode ());
        this.id (sale.getId ());
    }


}
