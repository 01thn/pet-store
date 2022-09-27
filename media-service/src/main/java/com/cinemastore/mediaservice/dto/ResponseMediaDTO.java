package com.cinemastore.mediaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMediaDTO {
    private String id;
    private String title;
    private Binary image;
}
