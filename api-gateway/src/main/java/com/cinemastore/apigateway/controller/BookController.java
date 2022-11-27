package com.cinemastore.apigateway.controller;

import com.cinemastore.apigateway.client.BookClient;
import com.cinemastore.apigateway.controller.helpers.BookEndpoints;
import com.cinemastore.apigateway.criteria.BookFilter;
import com.cinemastore.apigateway.dto.BookRequestDto;
import com.cinemastore.apigateway.dto.BookResponseDto;
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
@RequestMapping(BookEndpoints.BOOK)
public class BookController {
    private final BookClient client;

    public BookController(BookClient client) {
        this.client = client;
    }

    @PostMapping(BookEndpoints.ADD)
    public ResponseEntity<BookResponseDto> save(@Valid @RequestBody BookRequestDto requestDto) {
        return client.save(requestDto);
    }

    @GetMapping(BookEndpoints.GET_BY_ID)
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id, @RequestParam String imageId) {
        return client.getById(id, imageId);
    }

    @DeleteMapping(BookEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        return client.deleteById(id);
    }

    @PostMapping(BookEndpoints.FIND_BY)
    public ResponseEntity<List<BookResponseDto>> findBy(@RequestBody BookFilter bookFilter,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size) {
        return client.findBy(bookFilter, page, size);
    }
}
