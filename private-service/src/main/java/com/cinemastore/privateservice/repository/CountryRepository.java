package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    /**
     * @param title for searching
     * @return optional of country
     */
    Optional<Country> findByTitle(String title);

    /**
     * @param title for deleting
     */
    void deleteByTitle(String title);

    /**
     * @param films
     * @return list of entities by films
     */
    List<Country> findAllByFilmsIn(Set<Film> films);

    /**
     * @param series
     * @return list of entities by series
     */
    List<Country> findAllBySeriesIn(Set<Series> series);

    /**
     *
     * @return list of all entities
     */
    List<Country> findAll();
}
