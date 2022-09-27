package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.PersonEndpoints;
import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PersonResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(PersonEndpoints.PERSON)
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(PersonEndpoints.ADD)
    public ResponseEntity<PersonResponseDto> save(@RequestParam String firstName,
                                                  @RequestParam String lastName,
                                                  @RequestParam LocalDate dateOfBirth,
                                                  @RequestParam(required = false) LocalDate dateOfDeath) throws NoSuchContentException {
        return new ResponseEntity<>(personService.save(PersonRequestDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .dateOfDeath(dateOfDeath)
                .build()),
                HttpStatus.CREATED);
    }

    @GetMapping(PersonEndpoints.GET_BY_ID)
    public ResponseEntity<PersonResponseDto> getById(@PathVariable Long id) throws NoSuchContentException {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping(PersonEndpoints.GET_BY_FIRSTNAME)
    public ResponseEntity<List<PersonResponseDto>> getByFirstName(@RequestParam String firstName) {
        return new ResponseEntity<>(personService.findAllByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping(PersonEndpoints.GET_BY_LASTNAME)
    public ResponseEntity<List<PersonResponseDto>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(personService.findAllByLastName(lastName), HttpStatus.OK);
    }

    @DeleteMapping(PersonEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) throws NoSuchContentException {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
