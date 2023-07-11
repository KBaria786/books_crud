package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    public Optional<Genre> findByGenreName(String genreName);
}
