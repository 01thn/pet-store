package com.cinemastore.privateservice.service;

import com.cinemastore.privateservice.exception.NoSuchContentException;

import java.io.Serializable;
import java.util.List;

/**
 * Base interface for all services
 *
 * @param <L> id type
 * @param <S> entity type
 * @param <U> request dto type
 * @param <V> response dto type
 */
public interface BaseService<L extends Serializable, S, U, V> {

    /**
     * @param entity of info entity
     * @return saved entity
     */
    V save(U entity) throws NoSuchContentException;

    /**
     * @param id of info entity
     * @return entity
     */
    V findById(L id) throws NoSuchContentException;

    /**
     * @param id of info entity for deleting
     */
    void deleteById(L id) throws NoSuchContentException;

    /**
     * @param id     of info entity which must be updated
     * @param entity of info entity which must be updated
     * @return updated info entity
     */
    V updateById(L id, U entity) throws NoSuchContentException;
}
