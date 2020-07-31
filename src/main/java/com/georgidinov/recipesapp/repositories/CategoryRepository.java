package com.georgidinov.recipesapp.repositories;

import com.georgidinov.recipesapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {


    Optional<Category> findByDescription(String description);

}//end of interface CategoryRepository
