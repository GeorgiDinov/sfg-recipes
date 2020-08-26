package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.commands.IngredientCommand;
import com.georgidinov.recipesapp.commands.RecipeCommand;
import com.georgidinov.recipesapp.commands.UnitOfMeasureCommand;
import com.georgidinov.recipesapp.services.IngredientService;
import com.georgidinov.recipesapp.services.RecipeService;
import com.georgidinov.recipesapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    //== fields ==
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    //== constructors ==
    @Autowired
    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }//end of constructor


    //== public methods ==
    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipes/ingredients/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model) {
        model.addAttribute(
                "ingredient",
                this.ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id))
        );
        return "recipes/ingredients/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipeIngredient(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = this.recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList", this.unitOfMeasureService.listAllUnitOfMeasures());
        return "recipes/ingredients/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(
            @PathVariable String recipeId,
            @PathVariable String id,
            Model model) {
        model.addAttribute(
                "ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUnitOfMeasures());
        return "recipes/ingredients/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());
        String redirectionTo = "/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
        return "redirect:" + redirectionTo;
    }


}//end of class IngredientController
