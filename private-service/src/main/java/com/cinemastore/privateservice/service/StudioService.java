package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.dto.StudioResponseDto;
import com.cinemastore.privateservice.entity.Studio;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface StudioService extends BaseService<Integer, Studio, StudioRequestDto, StudioResponseDto> {

    /**
     * @param title for searching
     * @return found entity
     * @throws NoSuchContentException if not found
     */
    StudioResponseDto findByTitle(String title) throws NoSuchContentException;

    /**
     * @param title for deleting
     * @throws NoSuchContentException if doesn't exist
     */
    void deleteByTitle(String title) throws NoSuchContentException;

    /**
     * @param films for searcing
     * @return list of found entities
     */
    List<StudioResponseDto> findAllByFilmsIn(FilmRequestDto... films);

    /**
     * @param series
     * @return list of found entities
     */
    List<StudioResponseDto> findAllBySeriesIn(SeriesRequestDto... series);
}
