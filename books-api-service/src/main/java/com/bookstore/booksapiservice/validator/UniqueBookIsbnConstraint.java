package com.bookstore.booksapiservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBookIsbnValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBookIsbnConstraint {
    String message() default "invalid isbn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
