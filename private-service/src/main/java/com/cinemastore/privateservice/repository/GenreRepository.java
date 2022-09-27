package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    /**
     * @param title for searching
     * @return optional of genre
     */
    Optional<Genre> findByTitle(String title);

    /**
     * @param title for deleting
     */
    void deleteByTitle(String title);

    /**
     * @param books
     * @return list of entities by books
     */
    List<Genre> findAllByBooksIn(Set<Book> books);

    /**
     * @param films
     * @return list of entities by films
     */
    List<Genre> findAllByFilmsIn(Set<Film> films);

    /**
     * @param series
     * @return list of entities by series
     */
    List<Genre> findAllBySeriesIn(Set<Series> series);

    /**
     *
     * @return list of all entities
     */
    List<Genre> findAll();
}
