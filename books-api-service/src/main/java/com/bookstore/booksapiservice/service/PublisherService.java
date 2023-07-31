package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.PublisherSaveDto;
import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.repository.PublisherRepository;
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

@Service
@AllArgsConstructor
@Validated
public class PublisherService {

    private PublisherRepository publisherRepository;

    @Validated(OnSave.class)
    public Publisher save(@Valid PublisherSaveDto publisherSaveDto) {
        Publisher publisherToSave = Publisher.builder()
                .publisherName(publisherSaveDto.getPublisherName())
                .build();

        return publisherRepository.save(publisherToSave);
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public List<Publisher> findAll(Integer page, Integer limit) {
        return publisherRepository.findAll(PageRequest.of((page != null) ? page : 0, (limit != null) ? limit : 10)).toList();
    }

    public Publisher findById(int id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("publisher with id %d not found", id)));
    }

    @Validated(OnUpdate.class)
    public Publisher update(Integer id, @Valid PublisherSaveDto publisherSaveDto) {
        Publisher publisher = findById(id);
        Publisher publisherToSave = Publisher.builder()
                .publisherName(StringUtils.isNotEmpty(publisherSaveDto.getPublisherName()) ? publisherSaveDto.getPublisherName() : publisher.getPublisherName())
                .build();

        return publisherRepository.save(publisherToSave);
    }

    public void softDelete(Integer id) {
        Publisher publisher = findById(id);
        publisher.setDeleted(true);
        publisherRepository.save(publisher);
    }

    @Transactional
    public void hardDelete(Integer id) {
        if(publisherRepository.existsById(id)) {
            publisherRepository.updateBookPublishers(id);
            publisherRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException(String.format("publisher with id %d not found", id));
        }
    }

    public Long getTotalCount() {
        return publisherRepository.count();
    }

}
