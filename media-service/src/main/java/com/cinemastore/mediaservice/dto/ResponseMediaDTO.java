package com.cinemastore.mediaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMediaDTO {
    private String id;
    private String title;
    private Binary image;
}
