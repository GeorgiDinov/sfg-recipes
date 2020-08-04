package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    //== fields ==
    private final RecipeRepository recipeRepository;

    //== constructors ==
    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }//end of constructor


    //== public methods ==
    @RequestMapping({"/recipes", "recipesList", "recipes.html"})
    public String recipes(Model model){
        model.addAttribute("recipes", this.recipeRepository.findAll());
        return "recipes/recipes";
    }//end of method recipes

}//end of class RecipeController
