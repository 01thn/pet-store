package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.GenreResponseDto;
import com.cinemastore.privateservice.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper extends BaseMapper<Genre, GenreRequestDto, GenreResponseDto> {
    @Override
    Genre requestDtoToEntity(GenreRequestDto requestDto);

    @Override
    GenreResponseDto entityToResponseDto(Genre entity);

    @Override
    Genre updateEntityFromRequestDto(@MappingTarget Genre entity, GenreRequestDto requestDto);

    Genre responseDtoToEntity(GenreResponseDto responseDto);
}
