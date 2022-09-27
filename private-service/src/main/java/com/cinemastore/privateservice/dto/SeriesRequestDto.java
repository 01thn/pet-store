package com.cinemastore.privateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeriesRequestDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private LocalDate yearStarted;

    private LocalDate yearFinished;

    @NotBlank
    private Integer seasonAmount;

    private Integer ageLimit;

}
