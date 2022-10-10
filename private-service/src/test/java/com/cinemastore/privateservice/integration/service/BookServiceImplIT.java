package com.cinemastore.privateservice.integration.service;

import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.integration.IntegrationTestBase;
import com.cinemastore.privateservice.repository.BookRepository;
import com.cinemastore.privateservice.service.implementation.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.google.common.collect.Iterables;
import org.testcontainers.shaded.org.hamcrest.MatcherAssert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookServiceImplIT extends IntegrationTestBase {

    private final BookServiceImpl bookService;

    private final BookRepository bookRepository;

    private BookRequestDto firstExpected;

    private BookRequestDto secondExpected;

    BookServiceImplIT(BookServiceImpl bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void setup(){
        firstExpected = BookRequestDto.builder()
                .title("test1")
                .genres(new HashSet<>())
                .publishers(new HashSet<>())
                .authors(new HashSet<>())
                .pagesAmount(10)
                .releaseDate(LocalDate.of(2021, 1, 1))
                .build();

        secondExpected = BookRequestDto.builder()
                .title("test2")
                .genres(new HashSet<>())
                .publishers(new HashSet<>())
                .authors(new HashSet<>())
                .pagesAmount(20)
                .releaseDate(LocalDate.of(2022, 2, 2))
                .build();
    }

    @Test
    void save() throws NoSuchContentException {


        BookResponseDto firstActual = bookService.findById(bookService.save(firstExpected).getId());
        BookResponseDto secondActual = bookService.findById(bookService.save(secondExpected).getId());

        assertEquals(2, Iterables.size(bookRepository.findAll()));

        bookService.deleteById(firstActual.getId());
        bookService.deleteById(secondActual.getId());

        assertEquals(firstExpected.getTitle(), firstActual.getTitle());
        assertTrue(firstExpected.getGenres().isEmpty());
        assertTrue(firstExpected.getPublishers().isEmpty());
        assertTrue(firstExpected.getAuthors().isEmpty());
        assertEquals(firstExpected.getPagesAmount(), firstActual.getPagesAmount());
        assertEquals(firstExpected.getReleaseDate(), firstActual.getReleaseDate());

        assertEquals(secondExpected.getTitle(), secondActual.getTitle());
        assertTrue(secondExpected.getGenres().isEmpty());
        assertTrue(secondExpected.getPublishers().isEmpty());
        assertTrue(secondExpected.getAuthors().isEmpty());
        assertEquals(secondExpected.getPagesAmount(), secondActual.getPagesAmount());
        assertEquals(secondExpected.getReleaseDate(), secondActual.getReleaseDate());

    }

    @Test
    void findById() throws NoSuchContentException {

        BookResponseDto firstActual = bookService.findById(bookService.save(firstExpected).getId());
        bookService.deleteById(firstActual.getId());

        assertEquals(firstExpected.getTitle(), firstActual.getTitle());
        assertTrue(firstExpected.getGenres().isEmpty());
        assertTrue(firstExpected.getPublishers().isEmpty());
        assertTrue(firstExpected.getAuthors().isEmpty());
        assertEquals(firstExpected.getPagesAmount(), firstActual.getPagesAmount());
        assertEquals(firstExpected.getReleaseDate(), firstActual.getReleaseDate());
    }

    @Test
    void deleteById() throws NoSuchContentException {
        BookResponseDto firstActual = bookService.findById(bookService.save(firstExpected).getId());
        bookService.deleteById(firstActual.getId());
        assertEquals(0, Iterables.size(bookRepository.findAll()));
    }

    @Test
    void findBy() throws NoSuchContentException {
        bookService.save(firstExpected);
        BookResponseDto firstActual = bookService.findBy(BookFilter.builder()
                .title("test1")
                .build(), 0, 1)
                .get(0);

        bookService.deleteById(firstActual.getId());

        assertEquals(firstExpected.getTitle(), firstActual.getTitle());
        assertTrue(firstExpected.getGenres().isEmpty());
        assertTrue(firstExpected.getPublishers().isEmpty());
        assertTrue(firstExpected.getAuthors().isEmpty());
        assertEquals(firstExpected.getPagesAmount(), firstActual.getPagesAmount());
        assertEquals(firstExpected.getReleaseDate(), firstActual.getReleaseDate());
    }

    @Test
    void updateById() throws NoSuchContentException {
        BookResponseDto firstActual = bookService.findById(bookService.save(firstExpected).getId());
        BookResponseDto secondActual = bookService.updateById(firstActual.getId(), secondExpected);

        assertEquals(firstActual.getId(), secondActual.getId());
        bookService.deleteById(secondActual.getId());

        assertEquals(secondExpected.getTitle(), secondActual.getTitle());
        assertTrue(secondExpected.getGenres().isEmpty());
        assertTrue(secondExpected.getPublishers().isEmpty());
        assertTrue(secondExpected.getAuthors().isEmpty());
        assertEquals(secondExpected.getPagesAmount(), secondActual.getPagesAmount());
        assertEquals(secondExpected.getReleaseDate(), secondActual.getReleaseDate());
    }
}