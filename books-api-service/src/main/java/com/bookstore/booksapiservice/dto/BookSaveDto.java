package com.bookstore.booksapiservice.dto;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

import com.bookstore.booksapiservice.validator.BookIsbnConstraint;
import com.bookstore.booksapiservice.validator.BookTitleConstraint;
import com.bookstore.booksapiservice.validator.ExistingAuthorConstraint;
import com.bookstore.booksapiservice.validator.ExistingGenreConstraint;
import com.bookstore.booksapiservice.validator.ExistingPublisherConstraint;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveDto {

    private Integer id;

    @BookIsbnConstraint(message = "existing book with ISBN ${validatedValue}", groups = {OnSave.class})
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "\\d{9}-\\d", message = "invalid ISBN", groups = {OnSave.class, OnUpdate.class})
    private String isbn13;

    @BookTitleConstraint(message = "existing book with title ${validatedValue}", groups = OnSave.class)
    @NotBlank(message = "book title is required")
    private String title;

    @NotNull(message = "number of pages is required", groups = OnSave.class)
    @Min(value = 1, message = "number of pages should be greater than 1", groups = {OnSave.class, OnUpdate.class})
    private Integer numPages;

    @NotBlank(message = "description is required", groups = OnSave.class)
    private String description;

    @NotNull(message = "publication date is required", groups = OnSave.class)
    private Date publicationDate;

    @NotNull(message = "publisher is required", groups = OnSave.class)
    @ExistingPublisherConstraint(message = "publisher with id ${validatedValue} does not exist", 
    groups = {OnSave.class, OnUpdate.class})
    private Integer publisherId;
    
    @NotEmpty(message = "author details required", groups = OnSave.class)
    private Set<@ExistingAuthorConstraint(message = "author with id ${validatedValue} does not exist", 
    groups = {OnSave.class, OnUpdate.class}) Integer> authors;

    @NotEmpty(message = "genre details required", groups = OnSave.class)
    private Set<@ExistingGenreConstraint(message = "genre with id ${validatedValue} does not exist", 
    	    groups = {OnSave.class, OnUpdate.class}) Integer> genres;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

}
