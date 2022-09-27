package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.PublisherEndpoints;
import com.cinemastore.privateservice.dto.PublisherRequestDto;
import com.cinemastore.privateservice.dto.PublisherResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.PublisherService;
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
@RequestMapping(PublisherEndpoints.PUBLISHER)
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping(PublisherEndpoints.ADD)
    public ResponseEntity<PublisherResponseDto> save(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(publisherService.save(new PublisherRequestDto(title)), HttpStatus.CREATED);
    }

    @GetMapping(PublisherEndpoints.GET_BY_ID)
    public ResponseEntity<PublisherResponseDto> getById(@PathVariable Integer id) throws NoSuchContentException {
        return new ResponseEntity<>(publisherService.findById(id), HttpStatus.OK);
    }

    @GetMapping(PublisherEndpoints.GET_BY_TITLE)
    public ResponseEntity<PublisherResponseDto> getByTitle(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(publisherService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping(PublisherEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchContentException {
        publisherService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(PublisherEndpoints.DELETE_BY_TITLE)
    public ResponseEntity<HttpStatus> deleteByTitle(@RequestParam String title) throws NoSuchContentException {
        publisherService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(PublisherEndpoints.UPDATE)
    public ResponseEntity<PublisherResponseDto> update(@PathVariable Integer id, @RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(publisherService.updateById(id, new PublisherRequestDto(title)), HttpStatus.OK);
    }
}
