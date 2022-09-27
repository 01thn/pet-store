package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private UUID id;

    private Long userId;

    private Book book;

    private Film film;

    private Series series;

    private String title;

    private String comment;

    private Boolean isRecommend;
}
