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

import com.bookstore.booksapiservice.dto.PublisherSaveDto;
import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.service.PublisherService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("publishers")
@AllArgsConstructor
public class PublisherController {

    private PublisherService publisherService;

    @PostMapping()
    public ResponseEntity<Publisher> save(@RequestBody PublisherSaveDto publisherSaveDto) {
        return new ResponseEntity<>(publisherService.save(publisherSaveDto), HttpStatus.CREATED);
    }
    
    @GetMapping()
    public ResponseEntity<List<Publisher>> findAll() {
        return ResponseEntity.ok().body(publisherService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Publisher> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(publisherService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Publisher> update(@PathVariable("id") Integer id, @RequestBody PublisherSaveDto publisherSaveDto) {
        publisherSaveDto.setId(id);
        return new ResponseEntity<>(publisherService.update(id, publisherSaveDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Integer id) {
        publisherService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> hardDelete(@PathVariable("id") Integer id) {
        publisherService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }
    
}
