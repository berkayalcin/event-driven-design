package com.estore.productservice.query.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private String id;
    private String title;
    private BigDecimal price;
    private int quantity;
}
