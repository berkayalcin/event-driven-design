package com.estore.productservice.command.interceptor;

import com.estore.productservice.command.model.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, commandMessage) -> {
            log.info(commandMessage.getCommandName());
            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {
                log.info("Its Create Product Command");
            }
            return list.get(index);
        };
    }
}
