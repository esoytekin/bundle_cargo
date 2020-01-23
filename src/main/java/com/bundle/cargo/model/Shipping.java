package com.bundle.cargo.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Builder
public class Shipping {
    private long saleId;
    private boolean status;
    private Timestamp createdAt;
}
