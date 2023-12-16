package com.bookstore.booksapiservice.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booksapiservice.dto.GenreSaveDto;
import com.bookstore.booksapiservice.model.Genre;
import com.bookstore.booksapiservice.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("genres")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

    @Operation(summary = "Create a new genre")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "201", description = "created", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Genre.class))),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PostMapping()
    public ResponseEntity<Genre> save(@Parameter(description = "create a new genre", required = true)
                                          @RequestBody GenreSaveDto genreSaveDto) {
        return new ResponseEntity<>(genreService.save(genreSaveDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all genres")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Genre.class)))),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok().body(genreService.findAll());
    }

    @Operation(summary = "Find all genres with pagination")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Genre.class)))),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("paginated")
    public ResponseEntity<List<Genre>> findAll(@Parameter(name = "page", description = "page")
                                                   @Min(value = 0, message = "page should be greater than or equal to 0")
                                                   @RequestParam(name = "page", required = false) Integer page,
                                               @Parameter(name = "limit", description = "limit")
                                                   @Min(value = 0, message = "limit should be greater than or equal to 0")
                                                   @RequestParam(name = "limit", required = false) Integer limit) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", genreService.getTotalCount().toString());
        return ResponseEntity.ok().body(genreService.findAll(page, limit));
    }

    @Operation(summary = "Find genre by id")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Genre.class))),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @GetMapping("{id}")
    public ResponseEntity<Genre> findById(@Parameter(description = "genre id", required = true)
                                              @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(genreService.findById(id));
    }

    @Operation(summary = "Update genre")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200", description = "successful", content = @Content(schema = @Schema(implementation = Genre.class))),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "book not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<Genre> update(@Parameter(description = "genre id", required = true)
                                            @PathVariable("id") Integer id,
                                        @Parameter(description = "update genre", required = true)
                                        @RequestBody GenreSaveDto genreSaveDto) {
        genreSaveDto.setId(id);
        return new ResponseEntity<>(genreService.update(id, genreSaveDto), HttpStatus.OK);
    }

    @Operation(summary = "Mark genre as deleted")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "genre not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@Parameter(description = "genre id", required = true)
                                               @PathVariable("id") Integer id) {
        genreService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Mark genre as deleted")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "204", description = "no content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "genre not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)})
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> hardDelete(@Parameter(description = "genre id", required = true)
                                               @PathVariable("id") Integer id) {
        genreService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }
    
}
