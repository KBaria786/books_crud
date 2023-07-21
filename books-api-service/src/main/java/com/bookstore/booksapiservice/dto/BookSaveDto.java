package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.*;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "ISBN13", example = "693067420-6")
    @NotBlank(message = "ISBN is required", groups = {OnSave.class})
    @Pattern(regexp = "\\d{9}-\\d", message = "invalid ISBN", groups = {OnSave.class, OnUpdate.class})
    private String isbn13;

    @Schema(description = "Book title", example = "The Great Gatsby")
    @NotBlank(message = "book title is required", groups = {OnSave.class})
    private String title;

    @Schema(description = "Number of pages", example = "377")
    @NotNull(message = "number of pages is required", groups = OnSave.class)
    @Min(value = 1, message = "number of pages should be greater than 1", groups = {OnSave.class, OnUpdate.class})
    private Integer numPages;

    @Schema(description = "Book description", example = "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.")
    @NotBlank(message = "description is required", groups = OnSave.class)
    private String description;

    @Schema(description = "Book publication date", example = "2022-05-11", type = "string", format = "date")
    @NotNull(message = "publication date is required", groups = OnSave.class)
    private Date publicationDate;

    @Schema(description = "Existing publisher id", example = "22")
    @NotNull(message = "publisher is required", groups = OnSave.class)
    @ExistingPublisherConstraint(message = "publisher with id ${validatedValue} does not exist", 
    groups = {OnSave.class, OnUpdate.class})
    private Integer publisherId;

    @Schema(description = "Set of existing author ids", example = "[1]")
    @NotEmpty(message = "author details required", groups = OnSave.class)
    private Set<@ExistingAuthorConstraint(message = "author with id ${validatedValue} does not exist", 
    groups = {OnSave.class, OnUpdate.class}) Integer> authors;

    @Schema(description = "Set of existing genre ids", example = "[1]")
    @NotEmpty(message = "genre details required", groups = OnSave.class)
    private Set<@ExistingGenreConstraint(message = "genre with id ${validatedValue} does not exist",
            groups = {OnSave.class, OnUpdate.class})  Integer> genres;

    @JsonIgnore
    private Instant createdAt;

    @JsonIgnore
    private Instant updatedAt;

    @JsonIgnore
    private Boolean deleted;

}
