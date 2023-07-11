package com.bookstore.booksapiservice.model;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_book_sequence")
    @SequenceGenerator(name = "my_book_sequence", sequenceName = "book_sequence", initialValue = 183, allocationSize = 1)
    @Column(name = "book_id")
    private Integer id;

    //123456789-0
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^\\d{9}-\\d$", message = "invalid ISBN")
    private String isbn13;

    @NotBlank(message = "book title is required")
    private String title;

    @NotNull(message = "number of pages is required")
    @Min(value = 1, message = "number of pages should be greater than 1")
    private Integer numPages;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "publication date is required")
    private Date publicationDate;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    @Valid
    private Publisher publisher;

    @ManyToMany()
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @Valid
    private List<Author> authors;

    @ManyToMany()
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @Valid
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
