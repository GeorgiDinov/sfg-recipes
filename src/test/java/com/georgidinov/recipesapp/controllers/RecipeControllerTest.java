package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    void testMockMVC() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/recipes"));

    }//end of test method testMockMVC

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