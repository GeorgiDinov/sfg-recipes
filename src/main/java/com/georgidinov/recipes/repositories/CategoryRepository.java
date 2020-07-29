package com.georgidinov.recipes.repositories;

import com.georgidinov.recipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}//end of interface CategoryRepository
