package com.cinemastore.authservice.repository;

import com.cinemastore.authservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    /**
     * Finds a user by username
     * @param username of account
     * @return optional of found user
     */
    Optional<User> findByUsername(String username);

    /**
     * checks does user exist
     * @param username of account
     * @return result
     */
    boolean existsByUsername(String username);

    /**
     * checks does user exist
     * @param email of account
     * @return result
     */
    boolean existsByEmail(String email);
}
