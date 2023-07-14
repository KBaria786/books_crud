package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.PublisherRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistingPublisherValidator implements ConstraintValidator<ExistingPublisherConstraint, Integer> {
	
	private PublisherRepository publisherRepository;

	@Override
	public void initialize(ExistingPublisherConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Integer publisherId, ConstraintValidatorContext context) {
		return publisherId == null || publisherRepository.existsById(publisherId);
	}
	
}
