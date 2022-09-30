package com.cinemastore.authservice.service;

import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.NoSuchUserException;

import java.util.UUID;

public interface UserService {
    /**
     *
     * @param userRequestDto for saving
     * @return saved object
     * @throws NoSuchRoleException if mentioned role doesn't exist
     */
    UserResponseDto save(UserRequestDto userRequestDto) throws NoSuchRoleException;

    /**
     *
     * @param id for searching
     * @return found object
     * @throws NoSuchUserException if user with such id doesn't exist
     */
    UserResponseDto findById(UUID id) throws NoSuchUserException;

    /**
     *
     * @param username for searching
     * @return found object
     * @throws NoSuchUserException if user with such id doesn't exist
     */
    UserResponseDto findByUsername(String username) throws NoSuchUserException;

    /**
     *
     * @param username for checking
     * @return result of object existing
     */
    Boolean userExistsByUsername(String username);

    /**
     *
     * @param email for checking
     * @return result of object existing
     */
    Boolean userExistsByEmail(String email);

    /**
     *
     * @param id for deleting
     * @throws NoSuchUserException if user with such id doesn't exist
     */
    void deleteById(UUID id) throws NoSuchUserException;

    /**
     *
     * @param id for updating
     * @param entity with new data
     * @return
     * @throws NoSuchUserException if user with such id doesn't exist
     */
    UserResponseDto updateById(UUID id, UserRequestDto entity) throws NoSuchUserException;
}
