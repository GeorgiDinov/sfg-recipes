package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.IngredientCommand;
import com.georgidinov.recipesapp.converters.IngredientToIngredientCommand;
import com.georgidinov.recipesapp.domain.Ingredient;
import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    //== fields ==
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;


    //== constructors ==
    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }//end of constructor


    //== public methods ==
    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientCommandId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            //todo impl error handling
            log.error("recipe id {} not found", recipeId);
        }
        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommandId))
                .findFirst();

        if (ingredientOptional.isEmpty()) {
            //todo impl error handling
            log.error("Ingredient id {} not found:  ", ingredientCommandId);
        }
        Ingredient ingredient = ingredientOptional.get();

        return ingredientToIngredientCommand.convert(ingredient);
    }

}//end of class IngredientServiceImpl
