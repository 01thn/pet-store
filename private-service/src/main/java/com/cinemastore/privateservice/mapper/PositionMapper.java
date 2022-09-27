package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.PositionRespDto;
import com.cinemastore.privateservice.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper<Position, PositionReqDto, PositionRespDto> {
    @Override
    Position requestDtoToEntity(PositionReqDto requestDto);

    @Override
    PositionRespDto entityToResponseDto(Position entity);

    @Override
    Position updateEntityFromRequestDto(@MappingTarget Position entity, PositionReqDto requestDto);
}
