package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Publisher;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

    /**
     * @param title for searching
     * @return optional of publisher
     */
    Optional<Publisher> findByTitle(String title);

    /**
     * @param title for deleting
     */
    void deleteByTitle(String title);

    /**
     * @param books
     * @return list of entities by books
     */
    List<Publisher> findAllByBooksIn(Set<Book> books);

    /**
     *
     * @return list of all entities
     */
    List<Publisher> findAll();
}
