package com.georgidinov.recipesapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    //== fields ==


    //== constructors ==




    //== public methods ==
    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        log.info("Received a file!");
    }


}//end of class ImageServiceImpl
