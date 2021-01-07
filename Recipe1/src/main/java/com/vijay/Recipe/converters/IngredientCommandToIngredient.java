package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.IngredientCommand;
import com.vijay.Recipe.model.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	
	
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}




	@Override
	@Nullable
	@Synchronized
	public Ingredient convert(IngredientCommand source) {
		
		if(source==null)
		{
		return null;
		}
		
		final Ingredient ingredient=new Ingredient();
		ingredient.setId(source.getId());
		
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setUom(uomConverter.convert(source.getUom()));
		
		return ingredient;
	}

}
