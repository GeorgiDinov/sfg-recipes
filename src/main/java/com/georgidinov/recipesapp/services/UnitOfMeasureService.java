package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitOfMeasures();

}//end of interface UnitOfMeasureService
