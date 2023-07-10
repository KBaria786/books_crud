package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.repository.PublisherRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PublisherNameValidator implements ConstraintValidator<PublisherNameConstraint, String> {

    private PublisherRepository publisherRepository;

    @Override
    public void initialize(PublisherNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !publisherRepository.findByPublisherName().isPresent();
    }
}
