package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.RecipeCommand;
import com.georgidinov.recipesapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long id);

}//end of interface RecipeService
