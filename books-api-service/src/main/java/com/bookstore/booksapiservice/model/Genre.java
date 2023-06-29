package com.bookstore.booksapiservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private Integer id;
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
