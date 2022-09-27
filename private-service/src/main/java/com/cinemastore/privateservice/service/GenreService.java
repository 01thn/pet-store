package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.GenreResponseDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface GenreService extends BaseService<Integer, Genre, GenreRequestDto, GenreResponseDto> {

    /**
     *
     * @param title for searching
     * @return found entity
     * @throws NoSuchContentException if not found
     */
    GenreResponseDto findByTitle(String title) throws NoSuchContentException;

    /**
     *
     * @param title for deleting
     * @throws NoSuchContentException if doesn't exist
     */
    void deleteByTitle(String title) throws NoSuchContentException;

    /**
     * @param books for searching
     * @return list of found entities
     */
    List<GenreResponseDto> findAllByBooksIn(BookRequestDto... books);

    /**
     * @param films for searching
     * @return list of found entities
     */
    List<GenreResponseDto> findAllByFilmsIn(FilmRequestDto... films);

    /**
     * @param series for searching
     * @return list of found entities
     */
    List<GenreResponseDto> findAllBySeriesIn(SeriesRequestDto... series);
}
