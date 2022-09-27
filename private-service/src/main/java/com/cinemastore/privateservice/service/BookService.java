package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.util.List;

public interface BookService extends BaseService<Long, Book, BookRequestDto, BookResponseDto> {

    @Override
    BookResponseDto save(BookRequestDto entity) throws NoSuchContentException;

    List<BookResponseDto> findBy(BookFilter bookFilter, Integer page, Integer size);
}
