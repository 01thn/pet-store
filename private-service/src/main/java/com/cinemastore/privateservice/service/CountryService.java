package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.CountryResponseDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface CountryService extends BaseService<Integer, Country, CountryRequestDto, CountryResponseDto> {
    /**
     * @param title for searching
     * @return found entity
     * @throws NoSuchContentException if not found
     */
    CountryResponseDto findByTitle(String title) throws NoSuchContentException;

    /**
     * @param title for deleting
     * @throws NoSuchContentException if doesn't exist
     */
    void deleteByTitle(String title) throws NoSuchContentException;

    /**
     * @param films for searching
     * @return list of found entities
     */
    List<CountryResponseDto> findAllByFilmsIn(FilmRequestDto... films);

    /**
     * @param series for searching
     * @return list of found entities
     */
    List<CountryResponseDto> findAllBySeriesIn(SeriesRequestDto... series);
}
