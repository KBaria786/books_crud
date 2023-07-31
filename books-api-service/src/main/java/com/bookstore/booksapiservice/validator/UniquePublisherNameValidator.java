package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.dto.PublisherSaveDto;
import com.bookstore.booksapiservice.repository.PublisherRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniquePublisherNameValidator implements ConstraintValidator<UniquePublisherNameConstraint, PublisherSaveDto> {

    private PublisherRepository publisherRepository;

    @Override
    public void initialize(UniquePublisherNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PublisherSaveDto publisherSaveDto, ConstraintValidatorContext constraintValidatorContext) {
        return publisherRepository.findByPublisherNameIgnoreCase(publisherSaveDto.getPublisherName())
                .filter(publisher -> !publisher.getId().equals(publisherSaveDto.getId()))
                .isEmpty();
    }
}
