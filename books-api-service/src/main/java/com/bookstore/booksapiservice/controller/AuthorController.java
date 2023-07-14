package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity<Author> save(@RequestBody AuthorSaveDto authorSaveDto) {
        return new ResponseEntity<>(authorService.save(authorSaveDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok().body(authorService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(authorService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Integer id, @RequestBody AuthorSaveDto authorSaveDto) {
        authorSaveDto.setId(id);
        return new ResponseEntity<>(authorService.update(id, authorSaveDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Integer id) {
        authorService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
