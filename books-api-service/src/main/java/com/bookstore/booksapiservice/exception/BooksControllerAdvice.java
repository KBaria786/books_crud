package com.bookstore.booksapiservice.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BooksControllerAdvice {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Void> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Void> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<Void> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Void> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        exception.getConstraintViolations().forEach(violation -> {
            System.out.println(String.format("property : %s, message : %s, invalid value : %s",
                    processPropertyPath(violation.getPropertyPath()), violation.getMessage(), violation.getInvalidValue()));
        });
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Void> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity<Void> dataIntegrityViolationExceptionHandler(DataAccessException exception) {
        System.out.println(exception.getRootCause().getMessage());
        return ResponseEntity.noContent().build();
    }

    private String processPropertyPath(Path propertyPath) {
        String fieldName = propertyPath.toString();
        String[] fieldNameArray = fieldName.replace("search.", "").split("(?=[A-Z])");
        return String.join("_", fieldNameArray).toLowerCase();
    }

}
