package com.cinemastore.authservice.service;

import com.cinemastore.authservice.dto.RoleRequestDto;
import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;

public interface RoleService {

    /**
     *
     * @param roleRequestDto for saving
     * @return saved object
     */
    RoleResponseDto save(RoleRequestDto roleRequestDto);

    /**
     *
     * @param id for searching
     * @return found object
     * @throws NoSuchRoleException if role with such id doesn't exist
     */
    RoleResponseDto findById(Integer id) throws NoSuchRoleException;

    /**
     *
     * @param id for deleting
     * @throws NoSuchRoleException if role with such id doesn't exist
     */
    void deleteById(Integer id) throws NoSuchRoleException;

    /**
     *
     * @param id for updating
     * @param entity with the newest data
     * @return updated object
     * @throws NoSuchRoleException if role with such id doesn't exist
     */
    RoleResponseDto updateById(Integer id, RoleRequestDto entity) throws NoSuchRoleException;
}
