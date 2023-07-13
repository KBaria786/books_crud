package com.bookstore.booksapiservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.service.GenreService;

import lombok.AllArgsConstructor;

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
        genreSaveDto.setId(id);
        return new ResponseEntity<>(genreService.update(id, genreSaveDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Integer id) {
        genreService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
    
}
