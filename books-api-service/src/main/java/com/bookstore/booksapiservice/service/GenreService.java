package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {

    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(int id) {
        return genreRepository.findById(id).orElseThrow(null);
    }

}
