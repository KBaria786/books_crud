package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.UniqueGenreNameConstraint;
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
@UniqueGenreNameConstraint(message = "existing genre with name ${validatedValue.genreName}", groups = {OnSave.class, OnUpdate.class})
public class GenreSaveDto {

    @JsonIgnore
    private Integer id;

    @Schema(name = "Genre name", example = "Fiction")
    @NotBlank(message = "genre name is required", groups = OnSave.class)
    private String genreName;

}
