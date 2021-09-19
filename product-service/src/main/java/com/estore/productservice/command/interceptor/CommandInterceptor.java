package com.estore.productservice.command.interceptor;

import com.estore.productservice.command.model.CreateProductCommand;
import com.estore.productservice.core.repository.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final ProductLookupRepository productLookupRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, commandMessage) -> {
            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {
                final var createProductCommand = (CreateProductCommand) commandMessage.getPayload();
                final var productLookup = productLookupRepository.findByIdOrTitle(createProductCommand.getId(), createProductCommand.getTitle());
                if (productLookup != null) {
                    throw new IllegalStateException(
                            String.format("Product with id %s or title %s already exists",
                                    createProductCommand.getId(),
                                    createProductCommand.getTitle())
                    );
                }
            }
            return list.get(index);
        };
    }
}
