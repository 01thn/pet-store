package com.cinemastore.privateservice.mapper;

import org.mapstruct.MappingTarget;

/**
 * @param <S> base entity type for mapper
 * @param <U> request dto entity type for mapper
 * @param <V> response dto entity type for mapper
 */
public interface BaseMapper<S, U, V> {
    S requestDtoToEntity(U requestDto);

    V entityToResponseDto(S entity);

    S updateEntityFromRequestDto(@MappingTarget S entity, U requestDto);
}
