package com.cinemastore.privateservice.criteria;

import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Book_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BookSpecificationBuilder implements SpecificationBuilder<BookFilter, Book> {

    @Override
    public Specification<Book> getSpecification(BookFilter bookFilter) {
        return Specification
                .where(findBookById(bookFilter.getId())
                                .and(findByBookTitle(bookFilter.getTitle())
                                                .and(findByReleaseDate(bookFilter.getReleaseDate()))
                                                .and(findByAgeLimit(bookFilter.getAgeLimit()))
                                                .and(findByRank(bookFilter.getRank()))
//                                .and(findByGenresIn(bookFilter.getGenres()))
                                )
                );
    }

    private Specification<Book> findBookById(Long id) {
        return (root, query, builder) -> Optional.ofNullable(id)
                .map(_id -> builder.equal(root.get(Book_.ID), id))
                .orElseGet(builder::conjunction);
    }

    private Specification<Book> findByBookTitle(String title) {
        return (root, query, builder) -> Optional.ofNullable(title)
                .map(_title -> builder.equal(root.get(Book_.TITLE), title))
                .orElseGet(builder::conjunction);
    }

    private Specification<Book> findByReleaseDate(LocalDate date) {
        return (root, query, builder) -> Optional.ofNullable(date)
                .map(_date -> builder.equal(root.get(Book_.RELEASE_DATE), date))
                .orElseGet(builder::conjunction);
    }

    private Specification<Book> findByAgeLimit(Integer ageLimit) {
        return (root, query, builder) -> Optional.ofNullable(ageLimit)
                .map(_ageLimit -> builder.equal(root.get(Book_.AGE_LIMIT), ageLimit))
                .orElseGet(builder::conjunction);
    }

    private Specification<Book> findByRank(Double rank) {
        return (root, query, builder) -> Optional.ofNullable(rank)
                .map(_rank -> builder.equal(root.get(Book_.RANK), rank))
                .orElseGet(builder::conjunction);
    }

//    private Specification<Book> findByGenresIn(Set<GenreRequestDto> genres) {
//        Set<Genre> collect = genres.stream().map(genreMapper::requestDtoToEntity).collect(Collectors.toSet());
//        return (root, query, builder) -> Optional.ofNullable(collect)
//                .map(_genre -> builder.equal(root.get(Book_.GENRES).as(Genre.class), collect))
//                .orElseGet(builder::conjunction);
//    }

//    private Specification<Book> findByGenresIn(Set<Integer> genres) {
//        return (root, query, criteriaBuilder) ->
//                Optional.ofNullable(genres)
//                        .filter(value -> !genres.isEmpty())
//                        .map(values -> criteriaBuilder.and(root.get(Book_.GENRES).as(Genre.class).in(values)))
//                        .orElseGet(criteriaBuilder::conjunction);
//    }
}
