package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    //== fields ==
    private final RecipeRepository recipeRepository;


    //== constructors ==
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
        if (recipeOptional.isEmpty()){
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

}//end of class RecipeServiceImpl
