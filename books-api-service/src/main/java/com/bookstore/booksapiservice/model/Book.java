package com.bookstore.booksapiservice.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class Book {

    @Schema(name = "Book id", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_book_sequence")
    @SequenceGenerator(name = "my_book_sequence", sequenceName = "book_sequence", initialValue = 183, allocationSize = 1)
    @Column(name = "book_id")
    private Integer id;

    @Schema(description = "ISBN13", example = "693067420-6")
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^\\d{9}-\\d$", message = "invalid ISBN")
    @Column(unique = true)
    private String isbn13;

    @Schema(description = "Book title", example = "The Great Gatsby")
    @NotBlank(message = "book title is required")
    @Column(unique = true)
    private String title;

    @Schema(description = "Number of pages", example = "377")
    @NotNull(message = "number of pages is required")
    @Min(value = 1, message = "number of pages should be greater than 1")
    private Integer numPages;

    @Schema(description = "Number of pages", example = "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.")
    @NotBlank(message = "description is required")
    private String description;

    @Schema(description = "Book publication date", example = "2022-05-11", type = "string", format = "date")
    @NotNull(message = "publication date is required")
    private Date publicationDate;

    @Schema(description = "Publisher", implementation = Publisher.class)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id")
    @Valid
    private Publisher publisher;

    @ArraySchema(schema = @Schema(description = "Set of existing authors", implementation = Author.class))
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @Valid
    private Set<Author> authors;

    @ArraySchema(schema = @Schema(description = "Set of existing genres", implementation = Genre.class))
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @Valid
    private Set<Genre> genres;

    @Schema(description = "Creation timestamp")
    @CreationTimestamp()
    @Builder.Default
    private Instant createdAt = Instant.now();

    @Schema(description = "Update timestamp")
    @UpdateTimestamp
    @Builder.Default
    private Instant updatedAt = Instant.now();

    @Schema(description = "Deleted", example = "false")
    @Builder.Default
    private Boolean deleted = false;

}
