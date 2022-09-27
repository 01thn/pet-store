package com.cinemastore.privateservice.dto;

import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
