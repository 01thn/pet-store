package com.cinemastore.privateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionReqDto {

    private Integer id;

    @NotBlank
    private String title;

    public PositionReqDto(String title) {
        this.title = title;
    }
}
