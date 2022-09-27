package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.ReviewRequestDto;
import com.cinemastore.privateservice.dto.ReviewResponseDto;
import com.cinemastore.privateservice.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService extends BaseService<UUID, Review, ReviewRequestDto, ReviewResponseDto> {

    /**
     * @param userId for searching
     * @return list of entities
     */
    List<ReviewResponseDto> findAllByUserId(Long userId);
}
