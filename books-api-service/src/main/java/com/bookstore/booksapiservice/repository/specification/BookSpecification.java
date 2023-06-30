package com.bookstore.booksapiservice.repository.specification;

import com.bookstore.booksapiservice.dto.BookSearchDto;
import com.bookstore.booksapiservice.model.*;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookSpecification {

    public static Specification<Book> search(BookSearchDto bookSearchDto) {
        return ((root, query, criteriaBuilder) -> {
            Join<Book, Publisher> publisherJoin = root.join(Book_.publisher);
            Join<Book, Author> authorJoin = root.join(Book_.authors);
            Join<Book, Genre> genreJoin = root.join(Book_.genres);

            List<Predicate> predicates = new ArrayList<>();

            // equal queries
            if(StringUtils.isNotBlank(bookSearchDto.getTitle())) {
                predicates.add(criteriaBuilder.equal(root.get(Book_.title), bookSearchDto.getTitle()));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getIsbn13())) {
                predicates.add(criteriaBuilder.equal(root.get(Book_.isbn13), bookSearchDto.getIsbn13()));
            }
            if(bookSearchDto.getNumPages() != null) {
                predicates.add(criteriaBuilder.equal(root.get(Book_.numPages), bookSearchDto.getNumPages()));
            }
            if(bookSearchDto.getPublicationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get(Book_.publicationDate), bookSearchDto.getPublicationDate()));
            }
            if(bookSearchDto.getPublisherId() != null) {
                predicates.add(criteriaBuilder.equal(publisherJoin.get(Publisher_.id), bookSearchDto.getPublisherId()));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getPublisherName())) {
                predicates.add(criteriaBuilder.equal(publisherJoin.get(Publisher_.publisherName), bookSearchDto.getPublisherName()));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getAuthorName())) {
                predicates.add(criteriaBuilder.equal(authorJoin.get(Author_.authorName), bookSearchDto.getAuthorName()));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getGenreName())) {
                predicates.add(criteriaBuilder.equal(genreJoin.get(Genre_.genreName), bookSearchDto.getGenreName()));
            }

            // like queries
            if(StringUtils.isNotBlank(bookSearchDto.getTitleLike())) {
                predicates.add(criteriaBuilder.like(root.get(Book_.title), "%" + bookSearchDto.getTitleLike() + "%"));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getIsbn13Like())) {
                predicates.add(criteriaBuilder.like(root.get(Book_.isbn13), "%" + bookSearchDto.getIsbn13Like() + "%"));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getPublisherNameLike())) {
                predicates.add(criteriaBuilder.like(publisherJoin.get(Publisher_.publisherName), "%" + bookSearchDto.getPublisherNameLike() + "%"));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getAuthorNameLike())) {
                predicates.add(criteriaBuilder.like(authorJoin.get(Author_.authorName), "%" + bookSearchDto.getAuthorNameLike() + "%"));
            }
            if(StringUtils.isNotBlank(bookSearchDto.getGenreNameLike())) {
                predicates.add(criteriaBuilder.like(genreJoin.get(Genre_.genreName), "%" + bookSearchDto.getGenreNameLike() + "%"));
            }

            // in queries
            if(bookSearchDto.getAuthorsIn() != null && !bookSearchDto.getAuthorsIn().isEmpty()) {
                predicates.add(authorJoin.get(Author_.authorName).in(bookSearchDto.getAuthorsIn()));
            }
            if(bookSearchDto.getGenresIn() != null && !bookSearchDto.getGenresIn().isEmpty()) {
                predicates.add(genreJoin.get(Genre_.genreName).in(bookSearchDto.getGenresIn()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
