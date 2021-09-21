package com.estore.orderservice.core.handler.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private Date timestamp;
    private String message;
}
