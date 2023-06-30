package com.bookstore.booksapiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchDto {

    private String title;
    private String isbn13;
    private Integer numPages;
    private Date publicationDate;
    private Integer publisherId;
    private String publisherName;
    private String authorName;
    private String genreName;

    private String titleLike;
    private String isbn13Like;
    private String publisherNameLike;
    private String authorNameLike;
    private String genreNameLike;

    private Set<String> authorsIn;
    private Set<String> genresIn;

}
