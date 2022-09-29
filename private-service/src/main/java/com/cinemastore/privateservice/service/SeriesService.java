package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.criteria.SeriesFilter;
import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.SeriesResponseDto;
import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.time.LocalDate;
import java.util.List;

public interface SeriesService extends BaseService<Long, Series, SeriesRequestDto, SeriesResponseDto> {

    SeriesResponseDto findById(Long id, String imageId) throws NoSuchContentException;

    List<SeriesResponseDto> findBy(SeriesFilter seriesFilter, Integer page, Integer size);
}
