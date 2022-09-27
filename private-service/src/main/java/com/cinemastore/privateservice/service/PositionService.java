package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.PositionRespDto;
import com.cinemastore.privateservice.entity.Position;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface PositionService extends BaseService<Integer, Position, PositionReqDto, PositionRespDto> {

    /**
     * @param title for searcing
     * @return found entity
     * @throws NoSuchContentException if not found
     */
    PositionRespDto findByTitle(String title) throws NoSuchContentException;

    /**
     * @param title for searching
     * @throws NoSuchContentException if not found
     */
    void deleteByTitle(String title) throws NoSuchContentException;

    /**
     * @param persons for searching
     * @return list of found entities
     */
    List<PositionRespDto> findAllByPersonsIn(PersonRequestDto... persons);
}
