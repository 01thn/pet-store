package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PersonResponseDto;
import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonService extends BaseService<Long, Person, PersonRequestDto, PersonResponseDto> {
    /**
     * @param firstName for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByFirstName(String firstName);

    /**
     * @param lastName for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByLastName(String lastName);

    /**
     * @param dateOfBirth for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByDateOfBirth(LocalDate dateOfBirth);

    /**
     * @param dateOfDeath for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByDateOfDeath(LocalDate dateOfDeath);

    /**
     * @param roles for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByPositionsIn(PositionReqDto... roles);

    /**
     * @param countries for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByCountriesIn(CountryRequestDto... countries);

    /**
     * @param films for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByFilmsAuthorIn(FilmRequestDto... films);

    /**
     * @param films for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByFilmsOperatorIn(FilmRequestDto... films);

    /**
     * @param films for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllByFilmsActorIn(FilmRequestDto... films);

    /**
     * @param series for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllBySeriesAuthorIn(SeriesRequestDto... series);

    /**
     * @param series for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllBySeriesOperatorIn(SeriesRequestDto... series);

    /**
     * @param series for searching
     * @return list of found entities
     */
    List<PersonResponseDto> findAllBySeriesActorIn(SeriesRequestDto... series);
}
