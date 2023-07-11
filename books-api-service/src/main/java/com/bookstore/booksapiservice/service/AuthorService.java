package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.repository.AuthorRepository;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

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

    public Author findById(int id) {
        return authorRepository.findById(id).orElseThrow(null);
    }

    public List<Author> findAllById(Set<Integer> authorIds) {
        return authorIds.stream().map(id -> findById(id)).toList();
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

}
