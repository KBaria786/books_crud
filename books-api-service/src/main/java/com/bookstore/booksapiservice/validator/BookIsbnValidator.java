package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookIsbnValidator implements ConstraintValidator<BookIsbnConstraint, String> {

    private BookRepository bookRepository;

    @Override
    public void initialize(BookIsbnConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        return !bookRepository.findByIsbn13().isPresent();
    }
}
