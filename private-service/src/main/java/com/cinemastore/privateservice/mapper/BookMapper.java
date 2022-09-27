package com.cinemastore.privateservice.mapper;

import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<Book, BookRequestDto, BookResponseDto> {
    @Override
    Book requestDtoToEntity(BookRequestDto requestDto);

    @Override
    BookResponseDto entityToResponseDto(Book entity);

    @Override
    Book updateEntityFromRequestDto(@MappingTarget Book entity, BookRequestDto requestDto);
}
