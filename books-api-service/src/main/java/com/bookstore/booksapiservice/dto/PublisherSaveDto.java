package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.PublisherNameConstraint;
import com.bookstore.booksapiservice.validator.group.OnSave;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveDto {

    private Integer id;

    @PublisherNameConstraint(message = "existing publisher with name ${validatedValue}", groups = OnSave.class)
    @NotBlank(message = "publisher name is required", groups = OnSave.class)
    private String publisherName;

}
