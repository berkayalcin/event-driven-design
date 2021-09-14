package com.estore.productservice;

import com.estore.productservice.command.interceptor.CommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerCommandInterceptors(ApplicationContext applicationContext, CommandBus commandBus) {
        final var commandInterceptor = applicationContext.getBean(CommandInterceptor.class);
        commandBus.registerDispatchInterceptor(commandInterceptor);
    }
}
