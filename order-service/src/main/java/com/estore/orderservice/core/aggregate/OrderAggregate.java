package com.estore.orderservice.core.aggregate;

import com.estore.orderservice.command.enums.OrderStatus;
import com.estore.orderservice.command.event.OrderCreatedEvent;
import com.estore.orderservice.command.model.CreateOrderCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
public class OrderAggregate {
    @AggregateIdentifier
    private String id;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less or equal than zero");
        }

        if (createOrderCommand.getAddressId() == null || createOrderCommand.getAddressId().isBlank()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }

        if (createOrderCommand.getProductId() == null || createOrderCommand.getProductId().isBlank()) {
            throw new IllegalArgumentException("Product cannot be empty");
        }

        final var orderCreatedEvent = OrderCreatedEvent.builder()
                .orderStatus(createOrderCommand.getOrderStatus())
                .addressId(createOrderCommand.getAddressId())
                .productId(createOrderCommand.getProductId())
                .id(createOrderCommand.getId())
                .userId(createOrderCommand.getUserId())
                .quantity(createOrderCommand.getQuantity())
                .build();
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.id = orderCreatedEvent.getId();
        this.productId = orderCreatedEvent.getProductId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.addressId = orderCreatedEvent.getAddressId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        this.userId = orderCreatedEvent.getUserId();
    }

    @ExceptionHandler(resultType = Exception.class)
    public void error(Exception exp) {
        log.error(exp.getMessage());
    }
}
