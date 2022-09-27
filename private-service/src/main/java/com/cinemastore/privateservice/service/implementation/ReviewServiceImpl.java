package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.ReviewRequestDto;
import com.cinemastore.privateservice.dto.ReviewResponseDto;
import com.cinemastore.privateservice.entity.Review;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.ReviewMapper;
import com.cinemastore.privateservice.repository.ReviewRepository;
import com.cinemastore.privateservice.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    private final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponseDto save(ReviewRequestDto entity) {
        return reviewMapper.entityToResponseDto(
                reviewRepository.save(reviewMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponseDto findById(UUID id) throws NoSuchContentException {
        return reviewRepository.findById(id)
                .map(reviewMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Review with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteById(UUID id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> reviewRepository.deleteById(it.getId()));
    }

    @Override
    public ReviewResponseDto updateById(UUID id, ReviewRequestDto entity) throws NoSuchContentException {
        Optional<Review> maybeReviewById = reviewRepository.findById(id);
        if (maybeReviewById.isPresent()) {
            Review review = reviewMapper.updateEntityFromRequestDto(maybeReviewById.get(), entity);
            review.setId(id);
            return reviewMapper.entityToResponseDto(reviewRepository.save(review));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> findAllByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId).stream()
                .map(reviewMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
