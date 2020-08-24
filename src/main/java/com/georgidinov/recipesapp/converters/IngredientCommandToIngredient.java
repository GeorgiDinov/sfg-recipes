package com.georgidinov.recipesapp.converters;

import com.georgidinov.recipesapp.commands.IngredientCommand;
import com.georgidinov.recipesapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    //== fields ==
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;


    //== constructors ==
    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }//edn of constructor


    //== public methods ==
    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }//end of method convert

}//end of class IngredientCommandToIngredient
