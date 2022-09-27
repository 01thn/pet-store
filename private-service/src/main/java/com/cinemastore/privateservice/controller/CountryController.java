package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.CountryEndpoints;
import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.CountryResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.CountryService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CountryEndpoints.COUNTRY)
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(CountryEndpoints.ADD)
    public ResponseEntity<CountryResponseDto> save(@Valid @RequestBody CountryRequestDto requestDto) throws NoSuchContentException {
        return new ResponseEntity<>(countryService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping(CountryEndpoints.GET_BY_ID)
    public ResponseEntity<CountryResponseDto> getById(@PathVariable Integer id) throws NoSuchContentException {
        return new ResponseEntity<>(countryService.findById(id), HttpStatus.OK);
    }

    @GetMapping(CountryEndpoints.GET_BY_TITLE)
    public ResponseEntity<CountryResponseDto> getByTitle(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(countryService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping(CountryEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchContentException {
        countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(CountryEndpoints.DELETE_BY_TITLE)
    public ResponseEntity<HttpStatus> deleteByTitle(@RequestParam String title) throws NoSuchContentException {
        countryService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
