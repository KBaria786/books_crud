package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.dto.BookSaveDto;
import com.bookstore.booksapiservice.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueBookIsbnValidator implements ConstraintValidator<UniqueBookIsbnConstraint, BookSaveDto> {

    private BookRepository bookRepository;

    @Override
    public void initialize(UniqueBookIsbnConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BookSaveDto bookSaveDto, ConstraintValidatorContext constraintValidatorContext) {
        return !bookRepository.findByIsbn13(bookSaveDto.getIsbn13())
                .filter(book -> book.getId() != bookSaveDto.getId())
                .isPresent();
    }
}
