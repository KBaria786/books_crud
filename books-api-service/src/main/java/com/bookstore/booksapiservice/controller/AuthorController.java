package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    @GetMapping()
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok().body(authorService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(authorService.findById(id));
    }
    
}
