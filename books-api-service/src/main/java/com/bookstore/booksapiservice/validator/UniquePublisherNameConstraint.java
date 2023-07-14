package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePublisherNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePublisherNameConstraint {

    String message() default "invalid publisher name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
