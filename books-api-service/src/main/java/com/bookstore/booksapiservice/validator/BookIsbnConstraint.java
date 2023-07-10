package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BookIsbnValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookIsbnConstraint {

    String message() default "invalid isbn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
