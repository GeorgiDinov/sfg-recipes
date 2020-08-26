package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.UnitOfMeasureCommand;
import com.georgidinov.recipesapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    //== fields ==
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    //== constructors ==
    @Autowired
    public UnitOfMeasureServiceImpl(
            UnitOfMeasureRepository unitOfMeasureRepository,
            UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }//end of constructor


    //== public methods ==
    @Override
    public Set<UnitOfMeasureCommand> listAllUnitOfMeasures() {
        return StreamSupport.stream(
                unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }//end of method listAllUnitOfMeasures

}//end of class UnitOfMeasureServiceImpl
