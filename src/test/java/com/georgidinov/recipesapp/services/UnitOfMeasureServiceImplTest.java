package com.georgidinov.recipesapp.services;

import com.georgidinov.recipesapp.commands.UnitOfMeasureCommand;
import com.georgidinov.recipesapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.georgidinov.recipesapp.domain.UnitOfMeasure;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UnitOfMeasureServiceImplTest {


    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand =
            new UnitOfMeasureToUnitOfMeasureCommand();


    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);

    }

    @Test
    void listAllUnitOfMeasures() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUnitOfMeasures();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }


}//end of class UnitOfMeasureServiceImplTest