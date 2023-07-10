package com.bookstore.booksapiservice.dto;

import com.bookstore.booksapiservice.validator.AuthorNameConstraint;
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
public class AuthorSaveDto {

    private Integer id;

    @AuthorNameConstraint(message = "existing author with name ${validatedValue}", groups = OnSave.class)
    @NotBlank(message = "author name is required", groups = OnSave.class)
    private String authorName;

}
