package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        //given
        Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipeSet.add(recipe);
        recipeSet.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.recipes(model);

        //then
        assertEquals("recipes/recipes", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }//end of test method

}//end of class RecipeControllerTest