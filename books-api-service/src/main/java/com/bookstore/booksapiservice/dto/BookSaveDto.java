package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.*;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@UniqueBookIsbnConstraint(message = "existing book with ISBN ${validatedValue.isbn13}", groups = {OnSave.class, OnUpdate.class})
@UniqueBookTitleConstraint(message = "existing book with title ${validatedValue.title}", groups = {OnSave.class, OnUpdate.class})
public class BookSaveDto {

    @JsonIgnore
    private Integer id;

    @NotBlank(message = "ISBN is required", groups = {OnSave.class})
    @Pattern(regexp = "\\d{9}-\\d", message = "invalid ISBN", groups = {OnSave.class, OnUpdate.class})
    private String isbn13;

    @NotBlank(message = "book title is required", groups = {OnSave.class})
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
            groups = {OnSave.class, OnUpdate.class})  Integer> genres;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

}
