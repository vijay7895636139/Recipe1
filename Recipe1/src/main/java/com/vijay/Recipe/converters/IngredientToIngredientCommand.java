package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.IngredientCommand;
import com.vijay.Recipe.model.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>{

	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	
	
	
	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}



	@Override
	@Nullable
	@Synchronized
	public IngredientCommand convert(Ingredient source) {
		if(source==null)
		{
		return null;
		}
		
		IngredientCommand ingredientCommand=new IngredientCommand();
		ingredientCommand.setId(source.getId());
		if(source.getRecipe()!=null)
		{
			ingredientCommand.setRecipeId(source.getRecipe().getId());
		}
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setUom(uomConverter.convert(source.getUom()));
		return ingredientCommand;
	}

}
