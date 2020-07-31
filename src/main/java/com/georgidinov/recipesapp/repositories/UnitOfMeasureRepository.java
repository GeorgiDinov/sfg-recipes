package com.georgidinov.recipesapp.repositories;

import com.georgidinov.recipesapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);

}//end of interface UnitOfMeasureRepository
