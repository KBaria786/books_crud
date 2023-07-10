package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = BookTitleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BookTitleConstraint {

    String message() default "invalid title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
