package com.georgidinov.recipesapp.repositories;

import com.georgidinov.recipesapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}//end of interface RecipeRepository
