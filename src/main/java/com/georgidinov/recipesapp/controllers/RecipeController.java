package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.commands.RecipeCommand;
import com.georgidinov.recipesapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipes/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = this.recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

}//end of class RecipeController
