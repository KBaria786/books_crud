package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.repository.AuthorRepository;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class AuthorService {

    private AuthorRepository authorRepository;

    @Validated(OnSave.class)
    public Author save(@Valid AuthorSaveDto authorSaveDto) {
        Author authorToSave = Author.builder()
                .authorName(authorSaveDto.getAuthorName())
                .build();

        return authorRepository.save(authorToSave);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public List<Author> findAll(Integer page, Integer limit) {
        return authorRepository.findAll(PageRequest.of((page != null) ? page : 0, (limit != null) ? limit : 10)).toList();
    }

    public Author findById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("author with id %d not found", id)));
    }

    public Set<Author> findAllById(Set<Integer> authorIds) {
        return authorIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

    @Validated(OnUpdate.class)
    public Author update(Integer id, @Valid AuthorSaveDto authorSaveDto) {
        Author author = findById(id);
        Author authorToSave = Author.builder()
                .authorName(StringUtils.isNotEmpty(authorSaveDto.getAuthorName()) ? authorSaveDto.getAuthorName() : author.getAuthorName())
                .build();

        return authorRepository.save(authorToSave);
    }

    public void softDelete(Integer id) {
        Author author = findById(id);
        author.setDeleted(true);
        authorRepository.save(author);
    }

    @Transactional
    public void hardDelete(Integer id) {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteFromBookAuthors(id);
            authorRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException(String.format("author with id %d not found", id));
        }
    }

    public Long getTotalCount() {
        return authorRepository.count();
    }

}
