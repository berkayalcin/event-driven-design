package com.estore.orderservice.command.handler;

import com.estore.orderservice.command.event.OrderCreatedEvent;
import com.estore.orderservice.core.entity.Order;
import com.estore.orderservice.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup(value = "order-group")
public class OrderEventHandler {
    private final OrderRepository orderRepository;

    @SneakyThrows
    @EventHandler
    public void on(final OrderCreatedEvent orderCreatedEvent) {
        final var order = Order.builder()
                .orderStatus(orderCreatedEvent.getOrderStatus())
                .id(orderCreatedEvent.getId())
                .addressId(orderCreatedEvent.getAddressId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();
        orderRepository.save(order);
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException illegalArgumentException) {
        // Log error message
    }

    @ExceptionHandler()
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
