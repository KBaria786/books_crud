package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.GenreNameConstraint;
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
public class GenreSaveDto {

    private Integer id;

    @GenreNameConstraint(message = "existing genre with name ${validatedValue}", groups = OnSave.class)
    @NotBlank(message = "genre name is required", groups = OnSave.class)
    private String genreName;

}
