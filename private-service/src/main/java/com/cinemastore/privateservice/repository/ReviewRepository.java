package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Review;
import com.cinemastore.privateservice.entity.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends CrudRepository<Review, UUID> {

    /**
     * @param userId
     * @return list of entities by user
     */
    List<Review> findAllByUserId(Long userId);

    /**
     *
     * @return list of all entities
     */
    List<Review> findAll();
}
