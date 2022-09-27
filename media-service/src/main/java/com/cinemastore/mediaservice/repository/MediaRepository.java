package com.cinemastore.mediaservice.repository;

import com.cinemastore.mediaservice.entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

    /**
     * @param media entity for saving
     * @return Media if saved
     */
    @Override
    <S extends Media> S save(S media);

    /**
     * @param id of Media for searching
     * @return Optional of Media
     */
    Optional<Media> findById(String id);

    /**
     * @param media which must be deleted
     */
    void delete(Media media);

    /**
     *
     * @param id of media which must be deleted
     */
    void deleteById(String id);

    /**
     * @return list of all Media
     */
    List<Media> findAll();
}
