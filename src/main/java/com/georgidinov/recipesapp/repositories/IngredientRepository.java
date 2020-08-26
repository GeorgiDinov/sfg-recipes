package com.georgidinov.recipesapp.repositories;

import com.georgidinov.recipesapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
