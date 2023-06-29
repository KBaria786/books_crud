package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(null);
    }

}
