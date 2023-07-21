package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.AuthorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistingAuthorValidator implements ConstraintValidator<ExistingAuthorConstraint, Integer> {
	
	private AuthorRepository authorRepository;

	@Override
	public void initialize(ExistingAuthorConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Integer authorId, ConstraintValidatorContext context) {
		return authorId == null || authorRepository.existsById(authorId);
	}

}
