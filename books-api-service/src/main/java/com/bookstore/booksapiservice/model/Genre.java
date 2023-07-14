package com.bookstore.booksapiservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "genre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sequence")
    @SequenceGenerator(name = "genre_sequence", sequenceName = "genre_sequence", initialValue = 21, allocationSize = 1)
    @Column(name = "genre_id")
    private Integer id;

    @NotBlank(message = "genre name is required")
    @Column(unique = true)
    private String genreName;

    @CreationTimestamp
    @Builder.Default
    @JsonIgnore
    private Instant createdAt = Instant.now();

    @UpdateTimestamp
    @Builder.Default
    @JsonIgnore
    private Instant updatedAt = Instant.now();;

    @Builder.Default
    @JsonIgnore
    private Boolean deleted = false;
}
