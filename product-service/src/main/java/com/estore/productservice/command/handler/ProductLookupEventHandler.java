package com.estore.productservice.command.handler;

import com.estore.productservice.core.entity.ProductLookup;
import com.estore.productservice.core.event.ProductCreatedEvent;
import com.estore.productservice.core.repository.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup(value = "product-group")
public class ProductLookupEventHandler {
    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(final ProductCreatedEvent productCreatedEvent) {
        final var productLookup = ProductLookup.builder()
                .id(productCreatedEvent.getId())
                .title(productCreatedEvent.getTitle())
                .build();

        productLookupRepository.save(productLookup);
    }
}
