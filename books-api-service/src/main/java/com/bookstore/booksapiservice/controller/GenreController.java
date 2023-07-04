package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

    @PostMapping()
    public ResponseEntity<Genre> save(@RequestBody GenreSaveDto genreSaveDto) {
        return new ResponseEntity<>(genreService.save(genreSaveDto), HttpStatus.CREATED);
    }
    
    @GetMapping()
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok().body(genreService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Genre> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(genreService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Genre> update(@PathVariable("id") Integer id, @RequestBody GenreSaveDto genreSaveDto) {
        return new ResponseEntity<>(genreService.update(id, genreSaveDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id) {
        genreService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
    
}
