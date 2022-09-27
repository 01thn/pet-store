package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.FilmEndpoints;
import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.FilmService;
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

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping(FilmEndpoints.ADD)
    public ResponseEntity<FilmResponseDto> save(@Valid @RequestBody FilmRequestDto requestDto) throws NoSuchContentException {
        return new ResponseEntity<>(filmService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping(FilmEndpoints.GET_BY_ID)
    public ResponseEntity<FilmResponseDto> getById(@PathVariable Long id) throws NoSuchContentException {
        return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(FilmEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) throws NoSuchContentException {
        filmService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(FilmEndpoints.FIND_BY)
    public ResponseEntity<List<FilmResponseDto>> findBy(@RequestBody FilmFilter filmFilter,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size) {
        return new ResponseEntity<>(filmService.findBy(filmFilter, page, size), HttpStatus.OK);
    }
}
