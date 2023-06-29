package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("publishers")
@AllArgsConstructor
public class PublisherController {

    private PublisherService publisherService;

    @GetMapping()
    public ResponseEntity<List<Publisher>> findAll() {
        return ResponseEntity.ok().body(publisherService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Publisher> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(publisherService.findById(id));
    }
    
}
