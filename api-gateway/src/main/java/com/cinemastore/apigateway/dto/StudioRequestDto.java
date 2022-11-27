package com.cinemastore.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioRequestDto {

    private Integer id;

    @NotBlank
    private String title;

    public StudioRequestDto(String title) {
        this.title = title;
    }
}
