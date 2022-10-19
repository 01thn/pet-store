package com.cinemastore.apigateway.controller;

import com.cinemastore.apigateway.client.FilmClient;
import com.cinemastore.apigateway.client.SeriesClient;
import com.cinemastore.apigateway.controller.helpers.FilmEndpoints;
import com.cinemastore.apigateway.controller.helpers.SeriesEndpoints;
import com.cinemastore.apigateway.criteria.FilmFilter;
import com.cinemastore.apigateway.criteria.SeriesFilter;
import com.cinemastore.apigateway.dto.FilmRequestDto;
import com.cinemastore.apigateway.dto.FilmResponseDto;
import com.cinemastore.apigateway.dto.SeriesRequestDto;
import com.cinemastore.apigateway.dto.SeriesResponseDto;
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
@RequestMapping(SeriesEndpoints.SERIES)
public class SeriesController {
    private final SeriesClient client;

    public SeriesController(SeriesClient client) {
        this.client = client;
    }

    @PostMapping(SeriesEndpoints.ADD)
    public ResponseEntity<SeriesResponseDto> save(@Valid @RequestBody SeriesRequestDto requestDto) {
        return client.save(requestDto);
    }

    @GetMapping(SeriesEndpoints.GET_BY_ID)
    public ResponseEntity<SeriesResponseDto> getById(@PathVariable Long id, @RequestParam String imageId) {
        return client.getById(id, imageId);
    }

    @DeleteMapping(SeriesEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        return client.deleteById(id);
    }

    @PostMapping(SeriesEndpoints.FIND_BY)
    public ResponseEntity<List<SeriesResponseDto>> findBy(@RequestBody SeriesFilter seriesFilter,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size) {
        return client.findBy(seriesFilter, page, size);
    }
}
