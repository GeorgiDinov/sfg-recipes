package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    //== fields ==
    private final RecipeService recipeService;

    //== constructors ==
    @Autowired
    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }//end of constructor


    //== public methods ==
    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipes/ingredients/list";
    }


}//end of class IngredientController
