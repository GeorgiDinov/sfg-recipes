package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientCommandId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long id);
}//end of interface IngredientService
