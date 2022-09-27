package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Studio;
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

    private LocalDate yearStarted;

    private LocalDate yearFinished;

    private Integer seasonAmount;

    private Integer ageLimit;

    private Double rank;

    private Set<Country> countries = new HashSet<>();

    private Set<Genre> genres = new HashSet<>();

    private Set<Person> authors = new HashSet<>();

    private Set<Person> operators = new HashSet<>();

    private Set<Studio> studios = new HashSet<>();

    private Set<Person> actors = new HashSet<>();
}
