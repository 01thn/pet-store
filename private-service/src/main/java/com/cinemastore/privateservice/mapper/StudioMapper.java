package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.dto.StudioResponseDto;
import com.cinemastore.privateservice.entity.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudioMapper extends BaseMapper<Studio, StudioRequestDto, StudioResponseDto> {
    @Override
    Studio requestDtoToEntity(StudioRequestDto requestDto);

    @Override
    StudioResponseDto entityToResponseDto(Studio entity);

    @Override
    Studio updateEntityFromRequestDto(@MappingTarget Studio entity, StudioRequestDto requestDto);
}
