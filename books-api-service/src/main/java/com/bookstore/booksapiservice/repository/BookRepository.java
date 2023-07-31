package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book>  {

    public Optional<Book> findByIsbn13(String isbn13);

    public Optional<Book> findByTitleIgnoreCase(String title);

    @Modifying
    @Query(value = "delete from book_author where book_id = :bookId", nativeQuery = true)
    public int deleteFromBookAuthors(int bookId);

    @Modifying
    @Query(value = "delete from book_genre where book_id = :bookId", nativeQuery = true)
    public int deleteFromBookGenres(int bookId);

}
