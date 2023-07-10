package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenreNameConstraint {

    String message() default "invalid genre name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
