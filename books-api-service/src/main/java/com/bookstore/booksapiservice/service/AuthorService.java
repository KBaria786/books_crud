package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.repository.AuthorRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author save(AuthorSaveDto authorSaveDto) {
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

    public Author update(Integer id, AuthorSaveDto authorSaveDto) {
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
