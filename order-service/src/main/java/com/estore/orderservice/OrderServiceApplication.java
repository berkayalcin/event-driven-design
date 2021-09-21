package com.estore.orderservice;

import com.estore.orderservice.core.handler.OrderServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler("order-group", (configuration) -> new OrderServiceEventErrorHandler());
    }
}
