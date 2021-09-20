package com.estore.orderservice.command.rest;

import com.estore.orderservice.command.enums.OrderStatus;
import com.estore.orderservice.command.model.CreateOrderCommand;
import com.estore.orderservice.command.model.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String create(@RequestBody @Valid final CreateOrderRequest createOrderRequest) {
        final var createOrderCommand = CreateOrderCommand.builder()
                .orderStatus(OrderStatus.CREATED)
                .addressId(createOrderRequest.getAddressId())
                .quantity(createOrderRequest.getQuantity())
                .id(UUID.randomUUID().toString())
                .productId(createOrderRequest.getProductId())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .build();

        final var result = (String) commandGateway.sendAndWait(createOrderCommand);
        return result;
    }
}
