package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponseDto {

    private Integer id;

    private String title;
}
