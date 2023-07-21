package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBookTitleValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBookTitleConstraint {

    String message() default "invalid title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
