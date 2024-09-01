package com.higortavares.WebFluxCourse.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Constraint(validatedBy = {TrimStringValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface TrimString {
    String message() default "O campo não pode possuir espaços em branco no começo ou no fim";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
