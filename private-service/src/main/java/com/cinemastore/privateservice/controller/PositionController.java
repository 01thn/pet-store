package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.PositionEndpoints;
import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.PositionRespDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.PositionService;
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
@RequestMapping(PositionEndpoints.POSITION)
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping(PositionEndpoints.ADD)
    public ResponseEntity<PositionRespDto> save(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(positionService.save(new PositionReqDto(title)), HttpStatus.CREATED);
    }

    @GetMapping(PositionEndpoints.GET_BY_ID)
    public ResponseEntity<PositionRespDto> getById(@PathVariable Integer id) throws NoSuchContentException {
        return new ResponseEntity<>(positionService.findById(id), HttpStatus.OK);
    }

    @GetMapping(PositionEndpoints.GET_BY_TITLE)
    public ResponseEntity<PositionRespDto> getByTitle(@RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(positionService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping(PositionEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchContentException {
        positionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(PositionEndpoints.DELETE_BY_TITLE)
    public ResponseEntity<HttpStatus> deleteByTitle(@RequestParam String title) throws NoSuchContentException {
        positionService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(PositionEndpoints.UPDATE)
    public ResponseEntity<PositionRespDto> update(@PathVariable Integer id, @RequestParam String title) throws NoSuchContentException {
        return new ResponseEntity<>(positionService.updateById(id, new PositionReqDto(title)), HttpStatus.OK);
    }
}
