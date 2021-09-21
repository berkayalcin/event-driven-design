package com.estore.productservice.command.model;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    String id;
    String orderId;
    String userId;
    int quantity;
}
