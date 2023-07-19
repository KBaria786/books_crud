package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.BookSaveDto;
import com.bookstore.booksapiservice.dto.BookSearchDto;
import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.service.BookService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("books")
@AllArgsConstructor
@Validated
public class BooksController {

    private BookService bookService;

    @PostMapping()
    public ResponseEntity<Book> save(@RequestBody BookSaveDto bookSaveDto) {
        return new ResponseEntity<>(bookService.save(bookSaveDto), HttpStatus.CREATED);
    }

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
                                             @RequestParam(name = "isbn13", required = false)
                                                @Pattern(regexp = "^\\d{9}-\\d$", message = "date should be in yyyy-mm-dd format")
                                                String isbn13,
                                             @RequestParam(name = "num_pages", required = false)
                                                @Digits(integer = 10, fraction = 0, message = "number of pages should be an integer")
                                                Integer numPages,
                                             @RequestParam(name = "publication_date", required = false)
                                                @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date should be in yyyy-mm-dd format")
                                                String publicationDate,
                                             @RequestParam(name = "publisher_id", required = false)
                                                @Digits(integer = 10, fraction = 0, message = "publisher id should be an integer")
                                                Integer publisherId,
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
                .publicationDate(StringUtils.isNotBlank(publicationDate) ? Date.valueOf(publicationDate) : null)
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

    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Integer id, @RequestBody BookSaveDto bookSaveDto) {
        bookSaveDto.setId(id);
        return new ResponseEntity<>(bookService.update(id, bookSaveDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Integer id) {
        bookService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> hardDelete(@PathVariable("id") Integer id) {
        bookService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
