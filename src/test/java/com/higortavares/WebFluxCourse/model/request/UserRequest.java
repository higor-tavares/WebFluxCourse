package com.higortavares.WebFluxCourse.model.request;

public record UserRequest(
        String name,
        String email,
        String password
) { }
