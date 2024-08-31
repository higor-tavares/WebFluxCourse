package com.higortavares.WebFluxCourse.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ValidationError extends StandardError {

    private final List<FieldError> fieldErrorList = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, String path, Integer status, String error, String message) {
        super(timestamp, path, status, error, message);
    }

    @Getter
    @AllArgsConstructor
    private final static class FieldError {
        private String fieldName;
        private String message;
    }

    public void addError(String fieldName, String message) {
        this.fieldErrorList.add(new FieldError(fieldName, message));
    }
}
