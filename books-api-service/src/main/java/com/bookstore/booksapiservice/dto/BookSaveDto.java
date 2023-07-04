package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Genre;
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
    private String isbn13;
    private String title;
    private Integer numPages;
    private String description;
    private Date publicationDate;
    private Integer publisherId;
    private Set<Integer> authors;
    private Set<Integer> genres;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

}
