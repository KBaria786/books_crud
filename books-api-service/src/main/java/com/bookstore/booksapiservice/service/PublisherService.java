package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(int id) {
        return publisherRepository.findById(id).orElseThrow(null);
    }
    
}
