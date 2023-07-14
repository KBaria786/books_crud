package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.repository.GenreRepository;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class GenreService {

    private GenreRepository genreRepository;

    @Validated(OnSave.class)
    public Genre save(@Valid GenreSaveDto genreSaveDto) {
        Genre genreToSave = Genre.builder()
                .genreName(genreSaveDto.getGenreName())
                .build();

        return genreRepository.save(genreToSave);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(int id) {
        return genreRepository.findById(id).orElseThrow(null);
    }

    public Set<Genre> findAllById(Set<Integer> genreIds) {
        return genreIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

    @Validated(OnUpdate.class)
    public Genre update(Integer id, @Valid GenreSaveDto genreSaveDto) {
        Genre genre = findById(id);
        Genre genreToSave = Genre.builder()
                .genreName(StringUtils.isNotEmpty(genreSaveDto.getGenreName()) ? genreSaveDto.getGenreName() : genre.getGenreName())
                .build();

        return genreRepository.save(genreToSave);
    }

    public void softDelete(Integer id) {
        Genre genre = findById(id);
        genre.setDeleted(true);
        genreRepository.save(genre);
    }

    @Transactional
    public void hardDelete(Integer id) {
        genreRepository.deleteFromBookGenres(id);
        genreRepository.deleteById(id);
    }

}
