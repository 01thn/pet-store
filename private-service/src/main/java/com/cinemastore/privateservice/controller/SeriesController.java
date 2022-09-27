package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.FilmEndpoints;
import com.cinemastore.privateservice.controller.helpers.SeriesEndpoints;
import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.criteria.SeriesFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.SeriesResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.FilmService;
import com.cinemastore.privateservice.service.SeriesService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(SeriesEndpoints.SERIES)
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping(SeriesEndpoints.ADD)
    public ResponseEntity<SeriesResponseDto> save(@RequestParam String title,
                                                @RequestParam LocalDate yearStarted,
                                                @RequestParam(required = false) LocalDate yearFinished,
                                                @RequestParam Integer seasonAmount,
                                                @RequestParam(required = false) Integer ageLimit) throws NoSuchContentException {
        return new ResponseEntity<>(seriesService.save(SeriesRequestDto.builder()
                .title(title)
                .yearStarted(yearStarted)
                .yearFinished(yearFinished)
                .seasonAmount(seasonAmount)
                .ageLimit(ageLimit)
                .build()),
                HttpStatus.CREATED);
    }

    @GetMapping(SeriesEndpoints.GET_BY_ID)
    public ResponseEntity<SeriesResponseDto> getById(@PathVariable Long id) throws NoSuchContentException {
        return new ResponseEntity<>(seriesService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(SeriesEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) throws NoSuchContentException {
        seriesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(SeriesEndpoints.FIND_BY)
    public ResponseEntity<List<SeriesResponseDto>> findBy(@RequestBody SeriesFilter seriesFilter,
                                                          @RequestParam Integer page,
                                                          @RequestParam Integer size) {
        return new ResponseEntity<>(seriesService.findBy(seriesFilter, page, size), HttpStatus.OK);
    }
}
