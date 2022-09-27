package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Position;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * @param firstName
     * @return list of entities by first name
     */
    List<Person> findAllByFirstName(String firstName);

    /**
     * @param lastName
     * @return list of entities by last name
     */
    List<Person> findAllByLastName(String lastName);

    /**
     * @param dateOfBirth
     * @return list of entities by date of birth
     */
    List<Person> findAllByDateOfBirth(LocalDate dateOfBirth);

    /**
     * @param dateOfDeath
     * @return list of entities by date of birth
     */
    List<Person> findAllByDateOfDeath(LocalDate dateOfDeath);

    /**
     * @param positions
     * @return list of entities by roles
     */
    List<Person> findAllByPositionsIn(Set<Position> positions);

    /**
     * @param countries
     * @return list of entities by countries
     */
    List<Person> findAllByCountriesIn(Set<Country> countries);

    /**
     * @param filmsAuthor
     * @return list of entities by authors
     */
    List<Person> findAllByFilmsAuthorIn(Set<Film> filmsAuthor);

    /**
     * @param filmsOperator
     * @return list of entities by operators
     */
    List<Person> findAllByFilmsOperatorIn(Set<Film> filmsOperator);

    /**
     * @param filmsActor
     * @return list of entities by actors
     */
    List<Person> findAllByFilmsActorIn(Set<Film> filmsActor);

    /**
     * @param seriesAuthor
     * @return list of entities by series author
     */
    List<Person> findAllBySeriesAuthorIn(Set<Series> seriesAuthor);

    /**
     * @param seriesOperator
     * @return list of entities by operators
     */
    List<Person> findAllBySeriesOperatorIn(Set<Series> seriesOperator);

    /**
     * @param seriesActor
     * @return list of entities by actors
     */
    List<Person> findAllBySeriesActorIn(Set<Series> seriesActor);

    /**
     *
     * @return list of all entities
     */
    List<Person> findAll();
}
