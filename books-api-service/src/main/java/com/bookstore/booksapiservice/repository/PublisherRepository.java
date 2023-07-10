package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    public Optional<Publisher> findByPublisherName();
}
