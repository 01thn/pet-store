package com.cinemastore.privateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    @NotBlank
    private String title;

    @NotNull
    private Integer pagesAmount;

    @NotNull
    private LocalDate releaseDate;

    private Integer ageLimit;

    private Set<GenreRequestDto> genres = new HashSet<>();

    private Set<PublisherRequestDto> publishers = new HashSet<>();

    private Set<PersonRequestDto> authors = new HashSet<>();
}
