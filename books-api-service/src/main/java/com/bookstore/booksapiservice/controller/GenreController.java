package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genres")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

    @GetMapping()
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok().body(genreService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Genre> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(genreService.findById(id));
    }
    
}
