package com.estore.orderservice.saga;

import com.estore.orderservice.command.event.OrderCreatedEvent;
import com.estore.orderservice.core.model.User;
import com.estore.productservice.command.model.ReserveProductCommand;
import com.estore.productservice.core.event.ProductReservedEvent;
import com.estore.userservice.query.model.FetchUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(final OrderCreatedEvent orderCreatedEvent) {
        final var reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getId())
                .id(orderCreatedEvent.getProductId())
                .userId(orderCreatedEvent.getUserId())
                .quantity(orderCreatedEvent.getQuantity())
                .build();

        log.info("OrderCreatedEvent handled for orderId {} and productId {}", orderCreatedEvent.getId(), orderCreatedEvent.getProductId());

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start compensating transaction
                log.error(commandResultMessage.exceptionResult().getMessage());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId", payloadType = ProductReservedEvent.class)
    public void handle(ProductReservedEvent productReservedEvent) {
        // Process User-Payment
        log.info("ProductReservedEvent handled for orderId {} and productId {}", productReservedEvent.getOrderId(), productReservedEvent.getProductId());

        final var fetchUserPaymentDetailsQuery = FetchUserPaymentDetailsQuery.builder()
                .userId(productReservedEvent.getUserId())
                .build();

        try {
            final var user = queryGateway.query(fetchUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
            if (user == null) {
                // Start compensating transactions
                return;
            }
            log.info("Successfully fetched user payment details for user {}", user.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start compensating transactions
        }


    }

    @ExceptionHandler
    public void handleException(Exception exception) {
        log.error("Error", exception);
    }
}
