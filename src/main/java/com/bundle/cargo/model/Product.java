package com.bundle.cargo.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Product {
    private long id;
    private String category;
    private String name;
    private double price;
    private String image;
}
