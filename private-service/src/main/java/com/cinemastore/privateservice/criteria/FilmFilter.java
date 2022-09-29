package com.cinemastore.privateservice.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmFilter {

    private Long id;

    private String title;

    private String imageId;

    private Integer duration;

    private LocalDate releaseDate;

    private Integer ageLimit;

    private Double rank;
}
