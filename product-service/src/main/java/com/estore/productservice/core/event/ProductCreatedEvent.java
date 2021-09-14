package com.estore.productservice.core.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreatedEvent {
    private String id;
    private String title;
    private BigDecimal price;
    private int quantity;
}
