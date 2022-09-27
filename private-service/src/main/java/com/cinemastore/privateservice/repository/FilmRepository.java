package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Film;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long>, JpaSpecificationExecutor<Film> {
}
