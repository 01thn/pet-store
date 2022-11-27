package com.cinemastore.authservice.mapper;

import com.cinemastore.authservice.dto.RoleRequestDto;
import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleRequestDto, RoleResponseDto>{
    @Override
    Role requestDtoToEntity(RoleRequestDto requestDto);

    @Override
    RoleResponseDto entityToResponseDto(Role entity);

    @Override
    Role updateEntityFromRequestDto(@MappingTarget Role entity, RoleRequestDto requestDto);

    Role responseDtoToEntity(RoleResponseDto responseDto);
}
