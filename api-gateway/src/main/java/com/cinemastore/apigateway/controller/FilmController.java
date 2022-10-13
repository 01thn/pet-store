package com.cinemastore.apigateway.controller;

import com.cinemastore.apigateway.client.FilmClient;
import com.cinemastore.apigateway.controller.helpers.FilmEndpoints;
import com.cinemastore.apigateway.criteria.FilmFilter;
import com.cinemastore.apigateway.dto.FilmRequestDto;
import com.cinemastore.apigateway.dto.FilmResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(FilmEndpoints.FILM)
public class FilmController {
    private final FilmClient client;

    public FilmController(FilmClient client) {
        this.client = client;
    }

    @PostMapping(FilmEndpoints.ADD)
    public ResponseEntity<FilmResponseDto> save(@Valid @RequestBody FilmRequestDto requestDto) {
        return client.save(requestDto);
    }

    @GetMapping(FilmEndpoints.GET_BY_ID)
    public ResponseEntity<FilmResponseDto> getById(@PathVariable Long id, @RequestParam String imageId) {
        return client.getById(id, imageId);
    }

    @DeleteMapping(FilmEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        return client.deleteById(id);
    }

    @PostMapping(FilmEndpoints.FIND_BY)
    public ResponseEntity<List<FilmResponseDto>> findBy(@RequestBody FilmFilter filmFilter,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size) {
        return client.findBy(filmFilter, page, size);
    }
}
