package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueAuthorNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAuthorNameConstraint {

    String message() default "invalid author name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
