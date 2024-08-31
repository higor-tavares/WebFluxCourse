package com.higortavares.WebFluxCourse.model.response;

public record UserResponse(
        String id,
        String name,
        String email
) { }
