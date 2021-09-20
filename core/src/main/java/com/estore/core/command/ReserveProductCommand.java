package com.estore.core.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Value
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    String productId;
    String orderId;
    String userId;
    int quantity;
}
