package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.StudioEndpoints;
import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.dto.StudioResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.StudioService;
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
@RequestMapping(StudioEndpoints.STUDIO)
public class StudioController {
    private final StudioService studioService;

    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping(StudioEndpoints.ADD)
    public ResponseEntity<StudioResponseDto> save(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(studioService.save(new StudioRequestDto(title)), HttpStatus.CREATED);
    }

    @GetMapping(StudioEndpoints.GET_BY_ID)
    public ResponseEntity<StudioResponseDto> getById(@PathVariable Integer id) throws NoSuchContentException {
        return new ResponseEntity<>(studioService.findById(id), HttpStatus.OK);
    }

    @GetMapping(StudioEndpoints.GET_BY_TITLE)
    public ResponseEntity<StudioResponseDto> getByTitle(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(studioService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping(StudioEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchContentException {
        studioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(StudioEndpoints.DELETE_BY_TITLE)
    public ResponseEntity<HttpStatus> deleteByTitle(@RequestParam String title) throws NoSuchContentException {
        studioService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(StudioEndpoints.UPDATE)
    public ResponseEntity<StudioResponseDto> update(@PathVariable Integer id, @RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(studioService.updateById(id, new StudioRequestDto(title)), HttpStatus.OK);
    }
}
