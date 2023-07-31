package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueGenreNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueGenreNameConstraint {

    String message() default "invalid genre name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
