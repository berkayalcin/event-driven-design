package com.estore.productservice.core.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservedEvent {
    private String productId;
    private String orderId;
    private String userId;
    private int quantity;
}
