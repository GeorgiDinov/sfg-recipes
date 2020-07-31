package com.georgidinov.recipesapp.bootstrap;

import com.georgidinov.recipesapp.repositories.CategoryRepository;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    //== fields ==
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    //== constructors ==
    @Autowired
    public DataLoader(RecipeRepository recipeRepository,
                      CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }//end of constructor


    //== public methods ==
    @Override
    public void run(String... args) throws Exception {
        this.loadData();
    }//end of method run


    //== private methods ==
    private void loadData(){

    }//end of method loadData

}//end of class DataLoader
