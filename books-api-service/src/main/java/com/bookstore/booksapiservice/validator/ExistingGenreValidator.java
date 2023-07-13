package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.GenreRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistingGenreValidator implements ConstraintValidator<ExistingGenreConstraint, Integer> {

	private GenreRepository genreRepository;
	
	@Override
	public void initialize(ExistingGenreConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Integer genreId, ConstraintValidatorContext context) {
		return genreId != null ? genreRepository.existsById(genreId) : true;
	}

}
