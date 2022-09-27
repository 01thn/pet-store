package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface FilmService extends BaseService<Long, Film, FilmRequestDto, FilmResponseDto> {

    List<FilmResponseDto> findBy(FilmFilter filmFilter, Integer page, Integer size);
}
