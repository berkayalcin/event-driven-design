package com.estore.productservice.command.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    private String title;
    private BigDecimal price;
    private int quantity;
}
