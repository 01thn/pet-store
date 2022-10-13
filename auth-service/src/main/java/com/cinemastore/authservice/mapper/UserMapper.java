package com.cinemastore.authservice.mapper;

import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.entity.Role;
import com.cinemastore.authservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserRequestDto, UserResponseDto> {
    @Override
    User requestDtoToEntity(UserRequestDto requestDto);

    @Override
    UserResponseDto entityToResponseDto(User entity);

    @Override
    User updateEntityFromRequestDto(@MappingTarget User entity, UserRequestDto requestDto);

    User responseDtoToEntity(UserResponseDto responseDto);
}
