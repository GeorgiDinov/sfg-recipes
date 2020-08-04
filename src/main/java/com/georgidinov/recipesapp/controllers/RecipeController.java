package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    //== fields ==
    private final RecipeService recipeService;

    //== constructors ==
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    //== public methods ==
    @RequestMapping({"/recipes", "recipesList", "recipes.html"})
    public String recipes(Model model) {
        model.addAttribute("recipes", this.recipeService.getRecipes());
        return "recipes/recipes";
    }//end of method recipes

}//end of class RecipeController
