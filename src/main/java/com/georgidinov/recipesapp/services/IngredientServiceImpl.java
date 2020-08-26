package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.IngredientCommand;
import com.georgidinov.recipesapp.converters.IngredientCommandToIngredient;
import com.georgidinov.recipesapp.converters.IngredientToIngredientCommand;
import com.georgidinov.recipesapp.domain.Ingredient;
import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.domain.UnitOfMeasure;
import com.georgidinov.recipesapp.repositories.IngredientRepository;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    //== fields ==
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;


    //== constructors ==
    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
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


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (recipeOptional.isEmpty()) {
            //todo toss error if not found
            log.error("Recipe not found for id {}", command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional =
                    recipe.getIngredients()
                            .stream()
                            .filter(ingredient -> ingredient.getId().equals(command.getId()))
                            .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());

                Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId());
                unitOfMeasureOptional.ifPresent(ingredientFound::setUom);

            } else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.getIngredients().add(ingredient);

            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (savedIngredientOptional.isEmpty()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());

        }//end of else

    }//end of method saveIngredientCommand

    @Override
    public void deleteById(Long id) {
        this.ingredientRepository.deleteById(id);
    }

}//end of class IngredientServiceImpl
