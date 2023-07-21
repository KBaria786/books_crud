package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.UniqueAuthorNameConstraint;
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
@UniqueAuthorNameConstraint(message = "existing author with name ${validatedValue.authorName}", groups = {OnSave.class, OnUpdate.class})
public class AuthorSaveDto {

    @JsonIgnore
    private Integer id;

    @Schema(name = "Author name", example = "F. Scott Fitzgerald")
    @NotBlank(message = "author name is required", groups = OnSave.class)
    private String authorName;

}
