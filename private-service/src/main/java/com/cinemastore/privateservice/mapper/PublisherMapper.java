package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.GenreResponseDto;
import com.cinemastore.privateservice.dto.PublisherRequestDto;
import com.cinemastore.privateservice.dto.PublisherResponseDto;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends BaseMapper<Publisher, PublisherRequestDto, PublisherResponseDto> {
    @Override
    Publisher requestDtoToEntity(PublisherRequestDto requestDto);

    @Override
    PublisherResponseDto entityToResponseDto(Publisher entity);

    @Override
    Publisher updateEntityFromRequestDto(@MappingTarget Publisher entity, PublisherRequestDto requestDto);

    Publisher responseDtoToEntity(PublisherResponseDto responseDto);
}
