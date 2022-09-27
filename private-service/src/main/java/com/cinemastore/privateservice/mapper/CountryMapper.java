package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.CountryResponseDto;
import com.cinemastore.privateservice.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CountryMapper extends BaseMapper<Country, CountryRequestDto, CountryResponseDto> {
    @Override
    Country requestDtoToEntity(CountryRequestDto requestDto);

    @Override
    CountryResponseDto entityToResponseDto(Country entity);

    @Override
    Country updateEntityFromRequestDto(@MappingTarget Country entity, CountryRequestDto requestDto);
}
