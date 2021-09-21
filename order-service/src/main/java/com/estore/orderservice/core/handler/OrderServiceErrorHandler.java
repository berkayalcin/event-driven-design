package com.estore.orderservice.core.handler;

import com.estore.orderservice.core.handler.model.ErrorMessage;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class OrderServiceErrorHandler {

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException exception, WebRequest webRequest) {
        final var errorMessage = ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .build();
        return ResponseEntity.badRequest()
                .body(errorMessage);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException exception, WebRequest webRequest) {
        final var errorMessage = ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .build();
        return ResponseEntity.badRequest()
                .body(errorMessage);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
