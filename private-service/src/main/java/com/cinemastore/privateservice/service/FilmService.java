package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface FilmService extends BaseService<Long, Film, FilmRequestDto, FilmResponseDto> {

    FilmResponseDto findById(Long id, String imageId) throws NoSuchContentException;

    List<FilmResponseDto> findBy(FilmFilter filmFilter, Integer page, Integer size);
}
