package com.bookstore.booksapiservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_book_sequence")
    @SequenceGenerator(name = "my_book_sequence", sequenceName = "book_sequence", initialValue = 183, allocationSize = 1)
    @Column(name = "book_id")
    private Integer id;
    private String isbn13;
    private String title;
    private Integer numPages;
    private String description;
    private Date publicationDate;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany()
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToMany()
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @CreationTimestamp()
    @Builder.Default
    private Instant createdAt = Instant.now();

    @UpdateTimestamp
    @Builder.Default
    private Instant updatedAt = Instant.now();

    @Builder.Default
    private Boolean deleted = false;

}
