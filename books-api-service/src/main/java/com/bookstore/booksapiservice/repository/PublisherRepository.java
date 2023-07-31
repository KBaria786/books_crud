package com.bookstore.booksapiservice.repository;

import com.bookstore.booksapiservice.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>, PagingAndSortingRepository<Publisher, Integer> {
    public Optional<Publisher> findByPublisherNameIgnoreCase(String publisherName);

    @Modifying
    @Query(value = "update book set publisher_id = null where publisher_id = :publisherId", nativeQuery = true)
    public int updateBookPublishers(int publisherId);

}
