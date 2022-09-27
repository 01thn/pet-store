package com.cinemastore.privateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmRequestDto {

    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private Integer duration;

    @NotNull
    private LocalDate releaseDate;

    private Integer ageLimit;
}
