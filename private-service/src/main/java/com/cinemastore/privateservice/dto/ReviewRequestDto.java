package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {

    private UUID id;

    @NotBlank
    private Long userId;

    private Book book;

    private Film film;

    private Series series;

    @NotBlank
    private String title;

    private String comment;

    @NotBlank
    private Boolean isRecommend;
}
