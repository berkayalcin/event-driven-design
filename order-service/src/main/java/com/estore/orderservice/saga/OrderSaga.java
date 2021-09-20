package com.estore.orderservice.saga;

import com.estore.orderservice.command.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@RequiredArgsConstructor
public class OrderSaga {
    private final CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(final OrderCreatedEvent orderCreatedEvent) {

    }
}
