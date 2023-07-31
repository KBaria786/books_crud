package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, PagingAndSortingRepository<Author, Integer> {

    public Optional<Author> findByAuthorNameIgnoreCase(String authorName);

    @Modifying
    @Query(value = "delete from book_author where author_id = :authorId", nativeQuery = true)
    public int deleteFromBookAuthors(int authorId);

}
