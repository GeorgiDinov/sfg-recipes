package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

}//end of interface RecipeService
