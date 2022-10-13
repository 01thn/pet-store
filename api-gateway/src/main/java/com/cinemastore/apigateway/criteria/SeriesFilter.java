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
public class SeriesFilter {

    private Long id;

    private String title;

    private String imageId;

    private LocalDate yearStarted;

    private LocalDate yearFinished;

    private Integer seasonAmount;

    private Integer ageLimit;

    private Double rank;
}
