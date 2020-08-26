package com.georgidinov.recipesapp.controllers;

import com.georgidinov.recipesapp.services.ImageService;
import com.georgidinov.recipesapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    //== fields ==
    private final ImageService imageService;
    private final RecipeService recipeService;


    //== constructors ==
    @Autowired
    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }//end of constructor


    //== public methods ==
    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "/recipes/imageloadform";
    }


    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam("imagefile") MultipartFile file) {
        this.imageService.saveImageFile(Long.valueOf(id), file);
        String redirection = "/recipe/" + id + "/show";
        return "redirect:" + redirection;
    }


}//end of class ImageController
