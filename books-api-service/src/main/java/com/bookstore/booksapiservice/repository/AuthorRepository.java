package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Optional<Author> findByAuthorName();

}
