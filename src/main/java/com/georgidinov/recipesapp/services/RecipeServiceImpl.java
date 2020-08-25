package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.RecipeCommand;
import com.georgidinov.recipesapp.converters.RecipeCommandToRecipe;
import com.georgidinov.recipesapp.converters.RecipeToRecipeCommand;
import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    //== fields ==
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    //== constructors ==
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }//end of constructor

    //== public methods ==
    @Override
    public Set<Recipe> getRecipes() {
        log.info("Logg from the service class...");
        Set<Recipe> recipesSet = new HashSet<>();
        this.recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
        return recipesSet;
    }// end of method getRecipes

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(id);
        if (recipeOptional.isEmpty()) {
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        //from command obj to recipe obj
        Recipe detachedRecipe = this.recipeCommandToRecipe.convert(command);

        //saving the converted recipe obj
        Recipe savedRecipe = this.recipeRepository.save(detachedRecipe);

        log.debug("Saved Recipe Id = {} ", savedRecipe.getId());

        //returning the saved recipe obj from data base
        //and converted back to command obj
        return this.recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return this.recipeToRecipeCommand.convert(this.findById(id));
    }


}//end of class RecipeServiceImpl
