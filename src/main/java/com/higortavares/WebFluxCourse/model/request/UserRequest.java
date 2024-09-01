package com.higortavares.WebFluxCourse.model.request;

import com.higortavares.WebFluxCourse.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Size(min=3, max=50, message = "O nome precisa ter entre 3 e 50 caracteres")
        @NotBlank(message = "O Nome não pode ser nulo ou vazio")
        @TrimString
        String name,
        @TrimString
        @Email(message = "Email invalido")
        String email,
        @TrimString
        @Size(min=3, max=20, message = "A senha precisa ter entre 3 e 20 caracteres")
        @NotBlank(message = "A senha não pode ser nulo ou vazia")
        String password
) { }
