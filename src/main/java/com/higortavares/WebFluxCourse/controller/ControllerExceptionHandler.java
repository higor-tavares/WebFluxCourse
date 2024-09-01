package com.higortavares.WebFluxCourse.controller;

import com.higortavares.WebFluxCourse.exception.StandardError;
import com.higortavares.WebFluxCourse.exception.UserNotFoundException;
import com.higortavares.WebFluxCourse.exception.ValidationError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicatedKey(DuplicateKeyException ex, ServerHttpRequest request) {
        return ResponseEntity.badRequest().body(Mono.just(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .path(request.getPath().value())
                        .message("Email already exists")
                .build()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Mono<StandardError>> userNotFound(UserNotFoundException ex, ServerHttpRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .path(req.getPath().value())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("User not found")
                        .message(String.format("User with id [%s] not found!", ex.getId()))
                .build()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    ResponseEntity<Mono<ValidationError>> validationError(WebExchangeBindException ex, ServerHttpRequest request) {
        ValidationError error = new ValidationError(
                LocalDateTime.now(),
                request.getPath().value(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                "Error on validation attributes"
        );
        for(FieldError e: ex.getFieldErrors()) {
            error.addError(e.getField(), e.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(Mono.just(error));
    }
}
