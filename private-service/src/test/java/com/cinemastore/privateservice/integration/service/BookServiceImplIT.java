package com.cinemastore.privateservice.integration.service;

import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.integration.IntegrationTestBase;
import com.cinemastore.privateservice.repository.BookRepository;
import com.cinemastore.privateservice.service.implementation.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.google.common.collect.Iterables;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void setup() {
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
    void save() {
        BookResponseDto firstActual = null;
        BookResponseDto secondActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
            secondActual = bookService.findById(bookService.save(secondExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);
        assertNotNull(secondActual);
        assertEquals(2, Iterables.size(bookRepository.findAll()));

        try {
            bookService.deleteById(firstActual.getId());
            bookService.deleteById(secondActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }

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
    void findById() {

        BookResponseDto firstActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);

        try {
            bookService.deleteById(firstActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }

        assertEquals(firstExpected.getTitle(), firstActual.getTitle());
        assertTrue(firstExpected.getGenres().isEmpty());
        assertTrue(firstExpected.getPublishers().isEmpty());
        assertTrue(firstExpected.getAuthors().isEmpty());
        assertEquals(firstExpected.getPagesAmount(), firstActual.getPagesAmount());
        assertEquals(firstExpected.getReleaseDate(), firstActual.getReleaseDate());
    }

    @Test
    void deleteById() {
        BookResponseDto firstActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);

        try {
            bookService.deleteById(firstActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }
        assertEquals(0, Iterables.size(bookRepository.findAll()));
    }

    @Test
    void findBy() {
        try {
            bookService.save(firstExpected);
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        BookResponseDto firstActual = bookService.findBy(BookFilter.builder()
                        .title("test1")
                        .build(), 0, 1)
                .get(0);

        try {
            bookService.deleteById(firstActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }

        assertEquals(firstExpected.getTitle(), firstActual.getTitle());
        assertTrue(firstExpected.getGenres().isEmpty());
        assertTrue(firstExpected.getPublishers().isEmpty());
        assertTrue(firstExpected.getAuthors().isEmpty());
        assertEquals(firstExpected.getPagesAmount(), firstActual.getPagesAmount());
        assertEquals(firstExpected.getReleaseDate(), firstActual.getReleaseDate());
    }

    @Test
    void updateById() {
        BookResponseDto firstActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);
        BookResponseDto secondActual = null;

        try {
            secondActual = bookService.updateById(firstActual.getId(), secondExpected);
        } catch (NoSuchContentException e) {
            System.err.println("Problem during updating");
        }

        assertNotNull(secondActual);
        assertEquals(firstActual.getId(), secondActual.getId());

        try {
            bookService.deleteById(secondActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }

        assertEquals(secondExpected.getTitle(), secondActual.getTitle());
        assertTrue(secondExpected.getGenres().isEmpty());
        assertTrue(secondExpected.getPublishers().isEmpty());
        assertTrue(secondExpected.getAuthors().isEmpty());
        assertEquals(secondExpected.getPagesAmount(), secondActual.getPagesAmount());
        assertEquals(secondExpected.getReleaseDate(), secondActual.getReleaseDate());
    }

    @Test
    void tryToUpdateNotExisting() {
        BookResponseDto firstActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);
        BookResponseDto secondActual = null;

        try {
            secondActual = bookService.updateById(firstActual.getId() + 1, secondExpected);
        } catch (NoSuchContentException e) {
            System.err.println("Problem during updating");
        }

        assertNull(secondActual);

        try {
            bookService.deleteById(firstActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }
    }

    @Test
    void tryToDeleteNotExisting() {
        BookResponseDto firstActual = null;
        try {
            firstActual = bookService.findById(bookService.save(firstExpected).getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during saving");
        }

        assertNotNull(firstActual);

        try {
            bookService.deleteById(firstActual.getId() + 1);
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }
        assertEquals(1, Iterables.size(bookRepository.findAll()));

        try {
            bookService.deleteById(firstActual.getId());
        } catch (NoSuchContentException e) {
            System.err.println("Problem during deleting");
        }
    }
}