package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.domain.Category;
import com.georgidinov.recipesapp.domain.UnitOfMeasure;
import com.georgidinov.recipesapp.repositories.CategoryRepository;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    //== fields ==
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    //== constructors ==
    @Autowired
    public IndexController(CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }//end of constructor


    //== public methods ==
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional =
                this.categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                this.unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("Category id is: " + categoryOptional.get().getId());
        System.out.println("UnitOfMeasure id is: " + unitOfMeasureOptional.get().getId());
        return "index";
    }

}//end of class IndexController
