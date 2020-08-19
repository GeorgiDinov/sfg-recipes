package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RecipeControllerTest {

    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    RecipeController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeService);
    }//end of setUp method

    @Test
    void recipes() {
        String viewName = controller.recipes(model);
        assertEquals("recipes/recipes", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }//end of test method

}//end of class RecipeControllerTest