package com.estore.productservice.command.rest;

import com.estore.productservice.command.model.CreateProductCommand;
import com.estore.productservice.command.model.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {
    private final Environment environment;
    private final CommandGateway commandGateway;

    @PostMapping
    public String create(@RequestBody @Valid final CreateProductRequest createProductRequest) {
        final var createProductCommand = CreateProductCommand.builder()
                .price(createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .title(createProductRequest.getTitle())
                .id(UUID.randomUUID().toString())
                .build();

        final var result = (String) commandGateway.sendAndWait(createProductCommand);
        return "Http POST Handled " + result;
    }

    @PutMapping
    public String update() {
        return "Http PUT Handled";
    }

    @DeleteMapping
    public String delete() {
        return "Http DELETE Handled";
    }
}
