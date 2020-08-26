package com.georgidinov.recipesapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long id, MultipartFile file);

}//end of interface ImageService
