package com.cinemastore.privateservice.client.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmInfo {
    private String id;
    private Double chartRating;
}
