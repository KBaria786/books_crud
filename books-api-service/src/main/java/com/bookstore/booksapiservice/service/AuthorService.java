package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(int id) {
        return authorRepository.findById(id).orElseThrow(null);
    }

}
