package com.bookstore.booksapiservice.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class BooksControllerAdvice {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Http request method not supported")
                .message(exception.getMessage())
                .status(405)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Bad request")
                .message(exception.getMessage())
                .status(400)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Bad request")
                .message(exception.getMessage())
                .status(400)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ErrorResponse> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Media type not supported")
                .message(exception.getMessage())
                .status(400)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        ValidationErrorResponse validationErrorResponse = ValidationErrorResponse.builder()
                .error("Invalid input")
                .message(exception.getConstraintViolations().stream()
                        .map(violation -> ValidationError.builder()
                                .field(processPropertyPath(violation.getPropertyPath()))
                                .message(violation.getMessage())
                                .build())
                        .toList())
                .status(400)
                .build();

        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        System.out.println(exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Entity not found")
                .message(exception.getMessage())
                .status(404)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> dataIntegrityViolationExceptionHandler(DataAccessException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Internal server error")
                .message("Something went wrong")
                .status(500)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String processPropertyPath(Path propertyPath) {
        String fieldName = propertyPath.toString();
        String[] fieldNameArray = fieldName.replace("search.", "").split("(?=[A-Z])");
        return String.join("_", fieldNameArray).toLowerCase();
    }

}
