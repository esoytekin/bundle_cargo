package com.bundle.cargo.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Sale {

    private long id;
    private String saleCode;
    private long productId;
}
