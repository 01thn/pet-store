package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.ReviewRequestDto;
import com.cinemastore.privateservice.dto.ReviewResponseDto;
import com.cinemastore.privateservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<Review, ReviewRequestDto, ReviewResponseDto> {
    @Override
    Review requestDtoToEntity(ReviewRequestDto requestDto);

    @Override
    ReviewResponseDto entityToResponseDto(Review entity);

    @Override
    Review updateEntityFromRequestDto(@MappingTarget Review entity, ReviewRequestDto requestDto);
}
