package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long id;

    private String title;

    private Integer pagesAmount;

    private LocalDate releaseDate;

    private Integer ageLimit;

    private Double rank;

    private Set<GenreResponseDto> genres = new HashSet<>();

    private Set<PublisherResponseDto> publishers = new HashSet<>();

    private Set<PersonResponseDto> authors = new HashSet<>();
}
