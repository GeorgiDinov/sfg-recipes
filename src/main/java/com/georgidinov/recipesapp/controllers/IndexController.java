package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    //== fields ==
    private final RecipeService recipeService;

    //== constructors ==
    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }//end of controller

    //== public methods ==
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", this.recipeService.getRecipes());
        return "index";
    }

}//end of class IndexController
