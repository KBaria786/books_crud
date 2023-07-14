package com.bookstore.booksapiservice.validator;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.repository.AuthorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueAuthorNameValidator implements ConstraintValidator<UniqueAuthorNameConstraint, AuthorSaveDto> {

    private AuthorRepository authorRepository;

    @Override
    public void initialize(UniqueAuthorNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AuthorSaveDto authorSaveDto, ConstraintValidatorContext constraintValidatorContext) {
        return authorRepository.findByAuthorNameIgnoreCase(authorSaveDto.getAuthorName())
                .filter(author -> !(author.getId().equals(authorSaveDto.getId())))
                .isEmpty();
    }

}
