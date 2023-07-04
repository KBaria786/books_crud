package com.bookstore.booksapiservice.service;

import com.bookstore.booksapiservice.dto.PublisherSaveDto;
import com.bookstore.booksapiservice.model.Publisher;
import com.bookstore.booksapiservice.repository.PublisherRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    public Publisher save(PublisherSaveDto publisherSaveDto) {
        Publisher publisherToSave = Publisher.builder()
                .publisherName(publisherSaveDto.getPublisherName())
                .build();

        return publisherRepository.save(publisherToSave);
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(int id) {
        return publisherRepository.findById(id).orElseThrow(null);
    }

    public Publisher update(Integer id, PublisherSaveDto publisherSaveDto) {
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

}
