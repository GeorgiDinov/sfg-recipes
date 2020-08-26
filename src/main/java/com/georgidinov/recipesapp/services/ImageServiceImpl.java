package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    //== fields ==
    private final RecipeRepository recipeRepository;

    //== constructors ==
    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }//end of constructor


    //== public methods ==
    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(id).get();

            Byte[] imageBytes = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                imageBytes[i++] = b;
            }

            recipe.setImage(imageBytes);
            recipeRepository.save(recipe);
            log.info("Recipe image for recipe with id={} uploaded", id);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred {}", e.getMessage());
            e.printStackTrace();
        }

    }//end of method saveImageFile


}//end of class ImageServiceImpl
