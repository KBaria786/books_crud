package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.AuthorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorNameValidator implements ConstraintValidator<AuthorNameConstraint, String> {

    private AuthorRepository authorRepository;

    @Override
    public void initialize(AuthorNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !authorRepository.findByAuthorName().isPresent();
    }

}
