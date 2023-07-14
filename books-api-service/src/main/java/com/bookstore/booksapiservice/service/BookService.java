package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.BookSaveDto;
import com.bookstore.booksapiservice.dto.BookSearchDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.model.Book;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.repository.BookRepository;
import com.bookstore.booksapiservice.repository.specification.BookSpecification;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Validated
public class BookService {

    private BookRepository bookRepository;
    private PublisherService publisherService;
    private AuthorService authorService;
    private GenreService genreService;

    @Validated(OnSave.class)
    public Book save(@Valid BookSaveDto bookSaveDto) {
        Publisher publisher = publisherService.findById(bookSaveDto.getPublisherId());
        List<Author> authors = authorService.findAllById(bookSaveDto.getAuthors());
        List<Genre> genres = genreService.findAllById(bookSaveDto.getGenres());

        Book bookToSave = Book.builder()
                .title(bookSaveDto.getTitle())
                .isbn13(bookSaveDto.getIsbn13())
                .numPages(bookSaveDto.getNumPages())
                .description(bookSaveDto.getDescription())
                .publicationDate(bookSaveDto.getPublicationDate())
                .publisher(publisher)
                .authors(authors)
                .genres(genres)
                .build();

        return bookRepository.save(bookToSave);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Book not found"));
    }

    public List<Book> search(BookSearchDto bookSearchDto) {
        return bookRepository.findAll(BookSpecification.search(bookSearchDto));
    }

    @Validated(OnUpdate.class)
    public Book update(Integer id,@Valid BookSaveDto bookSaveDto) {
        Book book = findById(id);
        Publisher publisher = bookSaveDto.getPublisherId() != null ? publisherService.findById(bookSaveDto.getPublisherId()) : null;
        List<Author> authors = !CollectionUtils.isEmpty(bookSaveDto.getAuthors()) ? authorService.findAllById(bookSaveDto.getAuthors()) : List.of();
        List<Genre> genres = !CollectionUtils.isEmpty(bookSaveDto.getGenres()) ? genreService.findAllById(bookSaveDto.getGenres()) : List.of();

        Book bookToSave = Book.builder()
                .id(id)
                .title(StringUtils.isNotEmpty(bookSaveDto.getTitle()) ? bookSaveDto.getTitle() : book.getTitle())
                .isbn13(StringUtils.isNotEmpty(bookSaveDto.getIsbn13()) ? bookSaveDto.getIsbn13() : book.getIsbn13())
                .numPages(bookSaveDto.getNumPages() != null ? bookSaveDto.getNumPages() : book.getNumPages())
                .description(StringUtils.isNotEmpty(bookSaveDto.getDescription()) ? bookSaveDto.getDescription() : book.getDescription())
                .publicationDate(bookSaveDto.getPublicationDate() != null ? bookSaveDto.getPublicationDate() : book.getPublicationDate())
                .publisher(publisher != null ? publisher : book.getPublisher())
                .authors(authors.size() > 0 ? authors : book.getAuthors())
                .genres(genres.size() > 0 ? genres : book.getGenres())
                .build();

        return bookRepository.save(bookToSave);
    }

    public void softDelete(Integer id) {
        Book book = findById(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }

}