package com.estore.orderservice.command.model;

import com.estore.orderservice.command.enums.OrderStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    String id;
    String userId;
    String productId;
    int quantity;
    String addressId;
    OrderStatus orderStatus;
}
