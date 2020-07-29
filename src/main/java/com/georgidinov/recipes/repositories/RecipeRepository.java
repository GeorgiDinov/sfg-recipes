package com.georgidinov.recipes.repositories;

import com.georgidinov.recipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}//end of interface RecipeRepository
