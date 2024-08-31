package com.higortavares.WebFluxCourse.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardError {
    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}
