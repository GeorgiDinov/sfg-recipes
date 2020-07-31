package com.georgidinov.recipesapp.util.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Component
public class DecoupledLogicSetup {

    //== fields ==
    private final SpringResourceTemplateResolver templateResolver;


    //== constructors ==
    @Autowired
    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }//end of constructor


    //== init ==
    @PostConstruct
    public void inti() {
        this.templateResolver.setUseDecoupledLogic(true);
    }//end of method init

}//end of class DecoupledLogicSetup
