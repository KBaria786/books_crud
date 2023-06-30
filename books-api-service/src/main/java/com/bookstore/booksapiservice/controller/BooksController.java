package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.BookSearchDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("books")
@AllArgsConstructor
public class BooksController {

    private BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @GetMapping("search")
    public ResponseEntity<List<Book>> search(@RequestParam(name = "title", required = false) String title,
                                                @RequestParam(name = "isbn13", required = false) String isbn13,
                                                @RequestParam(name = "num_pages", required = false) Integer numPages,
                                                @RequestParam(name = "publication_date", required = false) Date publicationDate,
                                                @RequestParam(name = "publisher_id", required = false) Integer publisherId,
                                                @RequestParam(name = "publisher_name", required = false) String publisherName,
                                                @RequestParam(name = "author_name", required = false) String authorName,
                                                @RequestParam(name = "genre_name", required = false) String genreName,
                                                @RequestParam(name = "title_like", required = false) String titleLike,
                                                @RequestParam(name = "isbn13_like", required = false) String isbn13Like,
                                                @RequestParam(name = "publisher_name_like", required = false) String publisherNameLike,
                                                @RequestParam(name = "author_name_like", required = false) String authorNameLike,
                                                @RequestParam(name = "genre_name_like", required = false) String genreNameLike,
                                                @RequestParam(name = "authors_in", required = false) Set<String> authorsIn,
                                                @RequestParam(name = "genres_in", required = false) Set<String> genresIn) {
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .title(title)
                .isbn13(isbn13)
                .numPages(numPages)
                .publicationDate(publicationDate)
                .publisherId(publisherId)
                .publisherName(publisherName)
                .authorName(authorName)
                .genreName(genreName)
                .titleLike(titleLike)
                .isbn13Like(isbn13Like)
                .publisherNameLike(publisherNameLike)
                .authorNameLike(authorNameLike)
                .genreNameLike(genreNameLike)
                .authorsIn(authorsIn)
                .genresIn(genresIn)
                .build();

        return ResponseEntity.ok().body(bookService.search(bookSearchDto));
    }

}
