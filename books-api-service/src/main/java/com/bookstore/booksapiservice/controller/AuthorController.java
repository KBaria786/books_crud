package com.bookstore.booksapiservice.controller;

import com.bookstore.booksapiservice.dto.AuthorSaveDto;
import com.bookstore.booksapiservice.model.Author;
import com.bookstore.booksapiservice.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
@Tag(name = "author", description = "author api")
public class AuthorController {

    private AuthorService authorService;

    @Operation(summary = "Create a new author")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "201", description = "created", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PostMapping()
    public ResponseEntity<Author> save(@Parameter(description = "Create a new author", required = true)
                                           @RequestBody AuthorSaveDto authorSaveDto) {
        return new ResponseEntity<>(authorService.save(authorSaveDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all authors")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Author.class)))),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<Author>> findAll(@Parameter(name = "page", description = "page")
                                                    @Min(value = 0, message = "page should be greater than or equal to 0")
                                                        @RequestParam(name = "page", required = false) Integer page,
                                                @Parameter(name = "limit", description = "limit")
                                                    @Min(value = 0, message = "limit should be greater than or equal to 0")
                                                        @RequestParam(name = "limit", required = false) Integer limit) {
        return ResponseEntity.ok().body(authorService.findAll(page, limit));
    }

    @Operation(summary = "Find all authors with pagination")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Author.class)))),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("paginated")
    public ResponseEntity<List<Author>> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", authorService.getTotalCount().toString());
        return ResponseEntity.ok().body(authorService.findAll());
    }

    @Operation(summary = "Find an author by id")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "author not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@Parameter(description = "author id", required = true)
                                               @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(authorService.findById(id));
    }

    @Operation(summary = "Update an author")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "author not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<Author> update(@Parameter(description = "author id", required = true)
                                             @PathVariable("id") Integer id,
                                         @Parameter(description = "update an author", required = true)
                                         @RequestBody AuthorSaveDto authorSaveDto) {
        authorSaveDto.setId(id);
        return new ResponseEntity<>(authorService.update(id, authorSaveDto), HttpStatus.OK);
    }

    @Operation(summary = "Mark an author as deleted")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "author not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@Parameter(description = "author id", required = true)
                                               @PathVariable("id") Integer id) {
        authorService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an author")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "author not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> hardDelete(@Parameter(description = "author id", required = true)
                                               @PathVariable("id") Integer id) {
        authorService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
