package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookTitleValidator implements ConstraintValidator<BookTitleConstraint, String> {

    private BookRepository bookRepository;
    @Override
    public void initialize(BookTitleConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        return !bookRepository.findByTitle(title).isPresent();
    }
}
