package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.validator.BookIsbnConstraint;
import com.bookstore.booksapiservice.validator.BookTitleConstraint;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveDto {

    private Integer id;

    @BookIsbnConstraint(message = "existing book with ISBN ${validatedValue}", groups = OnSave.class)
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "/d{9}-/d", message = "invalid ISBN", groups = {OnSave.class, OnUpdate.class})
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
    private Integer publisherId;

    @NotEmpty(message = "author details required", groups = OnSave.class)
    private Set<Integer> authors;

    @NotEmpty(message = "genre details required", groups = OnSave.class)
    private Set<Integer> genres;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

}
