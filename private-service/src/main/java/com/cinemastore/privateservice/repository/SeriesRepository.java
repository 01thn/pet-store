package com.cinemastore.privateservice.repository;

import com.cinemastore.privateservice.entity.Series;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long>, JpaSpecificationExecutor<Series> {
}
