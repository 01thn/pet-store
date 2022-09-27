package com.cinemastore.privateservice.criteria;

import com.cinemastore.privateservice.entity.Book_;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.entity.Film_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class FilmSpecificationBuilder implements SpecificationBuilder<FilmFilter, Film> {

    @Override
    public Specification<Film> getSpecification(FilmFilter filmFilter) {
        return Specification
                .where(findFilmById(filmFilter.getId())
                        .and(findByFilmTitle(filmFilter.getTitle())
                                .and(findByDuration(filmFilter.getDuration()))
                                .and(findByReleaseDate(filmFilter.getReleaseDate()))
                                .and(findByAgeLimit(filmFilter.getAgeLimit()))
                                .and(findByRank(filmFilter.getRank()))

                        )
                );
    }

    private Specification<Film> findFilmById(Long id) {
        return (root, query, builder) -> Optional.ofNullable(id)
                .map(_id -> builder.equal(root.get(Film_.ID), id))
                .orElseGet(builder::conjunction);
    }

    private Specification<Film> findByFilmTitle(String title) {
        return (root, query, builder) -> Optional.ofNullable(title)
                .map(_title -> builder.equal(root.get(Film_.TITLE), title))
                .orElseGet(builder::conjunction);
    }

    private Specification<Film> findByDuration(Integer duration) {
        return (root, query, builder) -> Optional.ofNullable(duration)
                .map(_duration -> builder.equal(root.get(Film_.DURATION), duration))
                .orElseGet(builder::conjunction);
    }

    private Specification<Film> findByReleaseDate(LocalDate date) {
        return (root, query, builder) -> Optional.ofNullable(date)
                .map(_releaseDate -> builder.equal(root.get(Film_.RELEASE_DATE), date))
                .orElseGet(builder::conjunction);
    }

    private Specification<Film> findByAgeLimit(Integer ageLimit) {
        return (root, query, builder) -> Optional.ofNullable(ageLimit)
                .map(_ageLimit -> builder.equal(root.get(Film_.AGE_LIMIT), ageLimit))
                .orElseGet(builder::conjunction);
    }

    private Specification<Film> findByRank(Double rank) {
        return (root, query, builder) -> Optional.ofNullable(rank)
                .map(_rank -> builder.equal(root.get(Book_.RANK), rank))
                .orElseGet(builder::conjunction);
    }
}
