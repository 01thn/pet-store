package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FilmMapper extends BaseMapper<Film, FilmRequestDto, FilmResponseDto> {
    @Override
    Film requestDtoToEntity(FilmRequestDto requestDto);

    @Override
    FilmResponseDto entityToResponseDto(Film entity);

    @Override
    Film updateEntityFromRequestDto(@MappingTarget Film entity, FilmRequestDto requestDto);
}
