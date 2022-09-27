package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PersonResponseDto;
import com.cinemastore.privateservice.dto.PublisherResponseDto;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonRequestDto, PersonResponseDto> {
    @Override
    Person requestDtoToEntity(PersonRequestDto requestDto);

    @Override
    PersonResponseDto entityToResponseDto(Person entity);

    @Override
    Person updateEntityFromRequestDto(@MappingTarget Person entity, PersonRequestDto requestDto);

    Person responseDtoToEntity(PersonResponseDto responseDto);
}
