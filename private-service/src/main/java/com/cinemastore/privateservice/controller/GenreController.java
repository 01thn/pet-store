package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.GenreEndpoints;
import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.GenreResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GenreEndpoints.GENRE)
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(GenreEndpoints.ADD)
    public ResponseEntity<GenreResponseDto> save(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(genreService.save(new GenreRequestDto(title)), HttpStatus.CREATED);
    }

    @GetMapping(GenreEndpoints.GET_BY_ID)
    public ResponseEntity<GenreResponseDto> getById(@PathVariable Integer id) throws NoSuchContentException {
        return new ResponseEntity<>(genreService.findById(id), HttpStatus.OK);
    }

    @GetMapping(GenreEndpoints.GET_BY_TITLE)
    public ResponseEntity<GenreResponseDto> getByTitle(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(genreService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping(GenreEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchContentException {
        genreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(GenreEndpoints.DELETE_BY_TITLE)
    public ResponseEntity<HttpStatus> deleteByTitle(@RequestParam String title) throws NoSuchContentException {
        genreService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(GenreEndpoints.UPDATE)
    public ResponseEntity<GenreResponseDto> update(@PathVariable Integer id, @RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(genreService.updateById(id, new GenreRequestDto(title)), HttpStatus.OK);
    }
}
