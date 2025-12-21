package com.example.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidEmailValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface ValidEmail {

    // Required by Bean Validation: default message element
    String message() default "must be a well-formed email address";

    // Required by Bean Validation: groups element
    Class<?>[] groups() default {};

    // Required by Bean Validation: payload element
    Class<? extends Payload>[] payload() default {};

}
