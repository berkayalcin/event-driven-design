package com.estore.productservice.command.handler;

import com.estore.productservice.core.entity.Product;
import com.estore.productservice.core.event.ProductCreatedEvent;
import com.estore.productservice.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {
    private final ProductRepository productRepository;

    @EventHandler
    public void on(final ProductCreatedEvent productCreatedEvent) {
        final var product = Product.builder()
                .id(productCreatedEvent.getId())
                .price(productCreatedEvent.getPrice())
                .quantity(productCreatedEvent.getQuantity())
                .title(productCreatedEvent.getTitle())
                .build();

        productRepository.save(product);
    }
}
