package com.cinemastore.privateservice.criteria;

import com.cinemastore.privateservice.entity.Book_;
import com.cinemastore.privateservice.entity.Film_;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.entity.Series_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class SeriesSpecificationBuilder implements SpecificationBuilder<SeriesFilter, Series> {

    @Override
    public Specification<Series> getSpecification(SeriesFilter seriesFilter) {
        return Specification
                .where(findSeriesById(seriesFilter.getId())
                        .and(findBySeriesTitle(seriesFilter.getTitle())
                                .and(findByYearStarted(seriesFilter.getYearStarted()))
                                .and(findByYearFinished(seriesFilter.getYearFinished()))
                                .and(findByAgeLimit(seriesFilter.getAgeLimit()))
                                .and(findByRank(seriesFilter.getRank()))

                        )
                );
    }

    private Specification<Series> findSeriesById(Long id) {
        return (root, query, builder) -> Optional.ofNullable(id)
                .map(_id -> builder.equal(root.get(Film_.ID), id))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findBySeriesTitle(String title) {
        return (root, query, builder) -> Optional.ofNullable(title)
                .map(_title -> builder.equal(root.get(Film_.TITLE), title))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findByYearStarted(LocalDate date) {
        return (root, query, builder) -> Optional.ofNullable(date)
                .map(_date -> builder.equal(root.get(Series_.YEAR_STARTED), date))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findByYearFinished(LocalDate date) {
        return (root, query, builder) -> Optional.ofNullable(date)
                .map(_date -> builder.equal(root.get(Series_.YEAR_FINISHED), date))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findBySeasonAmount(Integer seasonAmount) {
        return (root, query, builder) -> Optional.ofNullable(seasonAmount)
                .map(_seasonAmount -> builder.equal(root.get(Film_.AGE_LIMIT), seasonAmount))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findByAgeLimit(Integer ageLimit) {
        return (root, query, builder) -> Optional.ofNullable(ageLimit)
                .map(_ageLimit -> builder.equal(root.get(Film_.AGE_LIMIT), ageLimit))
                .orElseGet(builder::conjunction);
    }

    private Specification<Series> findByRank(Double rank) {
        return (root, query, builder) -> Optional.ofNullable(rank)
                .map(_rank -> builder.equal(root.get(Book_.RANK), rank))
                .orElseGet(builder::conjunction);
    }
}
