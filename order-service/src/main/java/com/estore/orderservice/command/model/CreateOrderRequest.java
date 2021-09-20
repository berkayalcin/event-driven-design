package com.estore.orderservice.command.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    private String productId;
    private String addressId;
    private int quantity;
}
