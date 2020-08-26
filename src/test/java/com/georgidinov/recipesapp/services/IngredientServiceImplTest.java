package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.IngredientCommand;
import com.georgidinov.recipesapp.converters.IngredientCommandToIngredient;
import com.georgidinov.recipesapp.converters.IngredientToIngredientCommand;
import com.georgidinov.recipesapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.georgidinov.recipesapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.georgidinov.recipesapp.domain.Ingredient;
import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient =
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,
                ingredientCommandToIngredient, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setRecipe(recipe);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setRecipe(recipe);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setRecipe(recipe);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getIngredients().add(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = service.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }//end of test method findByRecipeIdAndIngredientId

    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.getIngredients().add(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = service.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

}//end of class IngredientServiceImplTest