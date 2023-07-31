package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.UniquePublisherNameConstraint;
import com.bookstore.booksapiservice.validator.group.OnSave;
import com.bookstore.booksapiservice.validator.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@UniquePublisherNameConstraint(message = "existing publisher with name ${validatedValue.publisherName}", groups = {OnSave.class, OnUpdate.class})
public class PublisherSaveDto {

    @JsonIgnore
    private Integer id;

    @Schema(name = "Publisher name", example = "Schmidt, Reilly and Sawayn")
    @NotBlank(message = "publisher name is required", groups = OnSave.class)
    private String publisherName;

}
