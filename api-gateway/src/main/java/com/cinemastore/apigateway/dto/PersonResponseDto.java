package com.cinemastore.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private LocalDate dateOfDeath;

}
