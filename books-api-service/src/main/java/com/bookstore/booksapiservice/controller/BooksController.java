package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
