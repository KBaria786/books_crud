package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PublisherNameConstraint {

    String message() default "invalid publisher name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
