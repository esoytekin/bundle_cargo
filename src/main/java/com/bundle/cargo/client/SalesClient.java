package com.bundle.cargo.client;

import com.bundle.cargo.model.Product;
import com.bundle.cargo.model.Sale;
import com.bundle.cargo.model.Shipping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sales-client", url = "${sales-client.url}")
public interface SalesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/sales/{saleId}")
    Sale findSaleById(@PathVariable("saleId") long saleId);

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    Product findProductById(@PathVariable("productId") long productId);

    @RequestMapping(method = RequestMethod.GET, value = "/shipping/{saleId}")
    Shipping findShippingBySaleId(@PathVariable("saleId") long saleId);
}
