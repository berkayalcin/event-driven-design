package com.estore.productservice.command.handler;

import com.estore.core.event.ProductReservedEvent;
import com.estore.productservice.core.entity.Product;
import com.estore.productservice.core.event.ProductCreatedEvent;
import com.estore.productservice.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup(value = "product-group")
public class ProductEventHandler {
    private final ProductRepository productRepository;

    @SneakyThrows
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

    @SneakyThrows
    @EventHandler
    public void on(final ProductReservedEvent productReservedEvent) {
        final var productOptional = productRepository.findById(productReservedEvent.getProductId());

        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException();
        }

        final var product = productOptional.get();
        product.setQuantity(product.getQuantity() - productReservedEvent.getQuantity());
        productRepository.save(product);
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException illegalArgumentException) {
        // Log error message
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
