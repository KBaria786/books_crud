package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.GenreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenreNameValidator implements ConstraintValidator<GenreNameConstraint, String> {

    private GenreRepository genreRepository;

    @Override
    public void initialize(GenreNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String genreName, ConstraintValidatorContext constraintValidatorContext) {
        return !genreRepository.findByGenreName(genreName).isPresent();
    }

}
