package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.repository.GenreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueGenreNameValidator implements ConstraintValidator<UniqueGenreNameConstraint, GenreSaveDto> {

    private GenreRepository genreRepository;

    @Override
    public void initialize(UniqueGenreNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(GenreSaveDto genreSaveDto, ConstraintValidatorContext constraintValidatorContext) {
        return !genreRepository.findByGenreNameIgnoreCase(genreSaveDto.getGenreName())
                .filter(genre -> genre.getId() != genreSaveDto.getId())
                .isPresent();
    }

}
