package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.BookSaveDto;
import com.bookstore.booksapiservice.dto.BookSearchDto;
import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.service.BookService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new book")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "201", description = "created", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PostMapping()
    public ResponseEntity<Book> save(@Parameter(description = "create a new book", required = true)
                                         @RequestBody BookSaveDto bookSaveDto) {
        return new ResponseEntity<>(bookService.save(bookSaveDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all books")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @Operation(summary = "Find a book by id")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@Parameter(description = "book id", required = true)
                                             @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @Operation(summary = "Filter books")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))),
            @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("search")
    public ResponseEntity<List<Book>> search(@Parameter(description = "title")
                                                 @RequestParam(name = "title", required = false) String title,
                                             @Parameter(description = "isbn13")
                                                @RequestParam(name = "isbn13", required = false)
                                                    @Pattern(regexp = "^\\d{9}-\\d$", message = "invalid isbn format")
                                                    String isbn13,
                                             @Parameter(description = "number of pages")
                                                @RequestParam(name = "num_pages", required = false)
                                                    @Pattern(regexp = "^\\d+$", message = "number of pages should be an integer")
                                                    String numPages,
                                             @Parameter(description = "publication date")
                                                @RequestParam(name = "publication_date", required = false)
                                                    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date should be in yyyy-mm-dd format")
                                                    String publicationDate,
                                             @Parameter(description = "publisher id")
                                                 @RequestParam(name = "publisher_id", required = false)
                                                    @Pattern(regexp = "^\\d+$", message = "publisher id should be an integer")
                                                    String publisherId,
                                             @Parameter(description = "publisher name")
                                                 @RequestParam(name = "publisher_name", required = false) String publisherName,
                                             @Parameter(description = "author name")
                                                 @RequestParam(name = "author_name", required = false) String authorName,
                                             @Parameter(description = "genre name")
                                                 @RequestParam(name = "genre_name", required = false) String genreName,
                                             @Parameter(description = "title like")
                                                 @RequestParam(name = "title_like", required = false) String titleLike,
                                             @Parameter(description = "isbn13 like")
                                                 @RequestParam(name = "isbn13_like", required = false) String isbn13Like,
                                             @Parameter(description = "publisher name like")
                                                 @RequestParam(name = "publisher_name_like", required = false) String publisherNameLike,
                                             @Parameter(description = "author name like")
                                                 @RequestParam(name = "author_name_like", required = false) String authorNameLike,
                                             @Parameter(description = "genre name like")
                                                 @RequestParam(name = "genre_name_like", required = false) String genreNameLike,
                                             @Parameter(description = "author names in")
                                                 @RequestParam(name = "authors_in", required = false) Set<String> authorsIn,
                                             @Parameter(description = "genre names in")
                                                 @RequestParam(name = "genres_in", required = false) Set<String> genresIn) {
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .title(title)
                .isbn13(isbn13)
                .numPages(StringUtils.isNotBlank(numPages) ? Integer.valueOf(numPages) : null)
                .publicationDate(StringUtils.isNotBlank(publicationDate) ? Date.valueOf(publicationDate) : null)
                .publisherId(StringUtils.isNotBlank(publisherId) ? Integer.valueOf(publisherId) : null)
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

    @Operation(summary = "Update book")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<Book> update(@Parameter(description = "book id", required = true)
                                           @PathVariable("id") Integer id,
                                       @Parameter(description = "update book", required = true)
                                       @RequestBody BookSaveDto bookSaveDto) {
        bookSaveDto.setId(id);
        return new ResponseEntity<>(bookService.update(id, bookSaveDto), HttpStatus.OK);
    }

    @Operation(summary = "Mark book as deleted")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@Parameter(description = "book id", required = true)
                                               @PathVariable("id") Integer id) {
        bookService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete book")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> hardDelete(@Parameter(description = "book id", required = true)
                                               @PathVariable("id") Integer id) {
        bookService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
