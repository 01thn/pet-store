package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.SeriesResponseDto;
import com.cinemastore.privateservice.entity.Series;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeriesMapper extends BaseMapper<Series, SeriesRequestDto, SeriesResponseDto> {
    @Override
    Series requestDtoToEntity(SeriesRequestDto requestDto);

    @Override
    SeriesResponseDto entityToResponseDto(Series entity);

    @Override
    Series updateEntityFromRequestDto(@MappingTarget Series entity, SeriesRequestDto requestDto);
}
