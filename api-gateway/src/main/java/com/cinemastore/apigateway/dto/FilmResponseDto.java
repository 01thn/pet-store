package com.cinemastore.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponseDto {

    private Long id;

    private byte[] image;

    private String title;

    private Integer duration;

    private Integer releaseDate;

    private Integer ageLimit;

    private Double rank;

    private Set<CountryResponseDto> countries = new HashSet<>();

    private Set<GenreResponseDto> genres = new HashSet<>();

    private Set<PersonResponseDto> authors = new HashSet<>();

    private Set<PersonResponseDto> operators = new HashSet<>();

    private Set<StudioResponseDto> studios = new HashSet<>();

    private Set<PersonResponseDto> actors = new HashSet<>();
}
