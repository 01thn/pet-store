package com.cinemastore.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaResponseDTO {
    private String id;
    private String title;
    private Binary image;
}
