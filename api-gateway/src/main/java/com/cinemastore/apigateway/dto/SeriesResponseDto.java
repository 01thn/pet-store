package com.cinemastore.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesResponseDto {

    private Long id;

    private String title;

    private byte[] image;

    private LocalDate yearStarted;

    private LocalDate yearFinished;

    private Integer seasonAmount;

    private Integer ageLimit;

    private Double rank;

    private Set<CountryResponseDto> countries = new HashSet<>();

    private Set<GenreResponseDto> genres = new HashSet<>();

    private Set<PersonResponseDto> authors = new HashSet<>();

    private Set<PersonResponseDto> operators = new HashSet<>();

    private Set<StudioResponseDto> studios = new HashSet<>();

    private Set<PersonResponseDto> actors = new HashSet<>();
}
