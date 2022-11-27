package com.cinemastore.apigateway.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {

    private Long id;

    private String title;

    private String imageId;

    private Integer pagesAmount;

    private LocalDate releaseDate;

    private Integer ageLimit;

    private Double rank;

//    private Set<Integer> genres = new HashSet<>();
//
//    private Set<PublisherRequestDto> publishers = new HashSet<>();
//
//    private Set<PersonRequestDto> authors = new HashSet<>();
}
