package com.bookstore.booksapiservice.model;

import java.sql.Date;
import java.time.Instant;

public class Book {

    private Integer id;
    private String isbn13;
    private String title;
    private Integer numPages;
    private String description;

    private Date publicationDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

}
