package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    public Optional<Genre> findByGenreNameIgnoreCase(String genreName);

    @Modifying
    @Query(value = "delete from book_genre where genre_id = :genreId", nativeQuery = true)
    public int deleteFromBookGenres(int genreId);

}
