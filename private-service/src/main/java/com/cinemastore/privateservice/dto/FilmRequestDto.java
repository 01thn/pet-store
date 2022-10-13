package com.cinemastore.privateservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonIgnore
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @JsonProperty("runningTimeInMinutes")
    private Integer duration;

    @NotNull
    @JsonProperty("year")
    private Integer releaseDate;

    private Integer ageLimit;

    private Double rank;
}
