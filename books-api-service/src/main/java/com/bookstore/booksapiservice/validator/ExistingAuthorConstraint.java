package com.bookstore.booksapiservice.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, TYPE_USE })
@Constraint(validatedBy = ExistingAuthorValidator.class)
public @interface ExistingAuthorConstraint {

    String message() default "author does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
