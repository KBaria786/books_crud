package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book>  {

    public Optional<Book> findByIsbn13(String isbn13);

    public Optional<Book> findByTitleIgnoreCase(String title);

}
