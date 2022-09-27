package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.PublisherRequestDto;
import com.cinemastore.privateservice.dto.PublisherResponseDto;
import com.cinemastore.privateservice.entity.Publisher;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface PublisherService extends BaseService<Integer, Publisher, PublisherRequestDto, PublisherResponseDto> {
    /**
     * @param title for searching
     * @return found entity
     * @throws NoSuchContentException if not found
     */
    PublisherResponseDto findByTitle(String title) throws NoSuchContentException;

    /**
     * @param title for deleting
     * @throws NoSuchContentException if doesn't exist
     */
    void deleteByTitle(String title) throws NoSuchContentException;

    /**
     * @param books for searching
     * @return list of entities
     */
    List<PublisherResponseDto> findAllByBooksIn(BookRequestDto... books);
}
