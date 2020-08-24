package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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
    @RequestMapping("recipe/show/{id}")
    public String getRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", this.recipeService.findById(Long.valueOf(id)));
        return "recipes/show";
    }//end of method recipes

}//end of class RecipeController
