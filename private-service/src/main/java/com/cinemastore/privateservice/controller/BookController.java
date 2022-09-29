package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.BookEndpoints;
import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.BookService;
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

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(BookEndpoints.ADD)
    public ResponseEntity<BookResponseDto> save(@Valid @RequestBody BookRequestDto requestDto) throws NoSuchContentException {
        return new ResponseEntity<>(bookService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping(BookEndpoints.GET_BY_ID)
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id, @RequestParam String imageId) throws NoSuchContentException {
        return new ResponseEntity<>(bookService.findById(id, imageId), HttpStatus.OK);
    }

    @DeleteMapping(BookEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) throws NoSuchContentException {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(BookEndpoints.FIND_BY)
    public ResponseEntity<List<BookResponseDto>> findBy(@RequestBody BookFilter bookFilter,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size) {
        return new ResponseEntity<>(bookService.findBy(bookFilter, page, size), HttpStatus.OK);
    }
}
