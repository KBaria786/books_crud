package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.repository.GenreRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class GenreService {

    private GenreRepository genreRepository;

    public Genre save(GenreSaveDto genreSaveDto) {
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

    public List<Genre> findAllById(Set<Integer> genreIds) {
        return genreIds.stream().map(id -> findById(id)).toList();
    }

    public Genre update(Integer id, GenreSaveDto genreSaveDto) {
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

}
