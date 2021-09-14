package com.estore.productservice.core.aggregate;

import com.estore.productservice.command.model.CreateProductCommand;
import com.estore.productservice.core.event.ProductCreatedEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String title;
    private BigDecimal price;
    private int quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be less or equal than zero");
        }
        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        final var productCreatedEvent = ProductCreatedEvent.builder()
                .id(createProductCommand.getId())
                .price(createProductCommand.getPrice())
                .quantity(createProductCommand.getQuantity())
                .title(createProductCommand.getTitle())
                .build();

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.id = productCreatedEvent.getId();
        this.price = productCreatedEvent.getPrice();
        this.title = productCreatedEvent.getTitle();
        this.quantity = productCreatedEvent.getQuantity();
    }

}
