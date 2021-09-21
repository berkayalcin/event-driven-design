package com.estore.productservice.core.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReservedEvent {
    String productId;
    String orderId;
    String userId;
    int quantity;
}
