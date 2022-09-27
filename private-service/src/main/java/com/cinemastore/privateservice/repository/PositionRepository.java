package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Position;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {

    /**
     * @param title for searching
     * @return optional of role
     */
    Optional<Position> findByTitle(String title);

    /**
     * @param id for deleting
     */
    void deleteById(Long id);

    /**
     * @param title for deleting
     */
    void deleteByTitle(String title);

    /**
     * @return list of all entities
     */
    List<Position> findAll();

    /**
     * @param persons
     * @return list of entities by persons
     */
    List<Position> findAllByPersonsIn(Set<Person> persons);
}
