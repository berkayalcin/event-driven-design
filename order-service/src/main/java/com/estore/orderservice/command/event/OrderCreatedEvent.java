package com.estore.orderservice.command.event;

import com.estore.orderservice.command.enums.OrderStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderCreatedEvent {
    String id;
    String userId;
    String productId;
    int quantity;
    String addressId;
    OrderStatus orderStatus;
}
