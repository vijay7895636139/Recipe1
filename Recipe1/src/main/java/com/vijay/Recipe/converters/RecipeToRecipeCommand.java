package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.model.Category;
import com.vijay.Recipe.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	
	
	
	public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand,
			CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommand notesToNotesCommand) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.notesToNotesCommand = notesToNotesCommand;
	}



	@Override
	@Nullable
	@Synchronized
	public RecipeCommand convert(Recipe source) {
		if(source==null)
		{
		return null;
		}
		final RecipeCommand recipeCommand=new RecipeCommand();
		recipeCommand.setId(source.getId());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setServing(source.getServing());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		
		if(source.getCategory()!=null && source.getCategory().size()>0)
		{
			source.getCategory()
			.forEach((Category category)->recipeCommand.getCategory()
			.add(categoryToCategoryCommand.convert(category)));
		}
		
		if(source.getIngredient()!=null && source.getIngredient().size()>0)
		{
			source.getIngredient()
			.forEach(ingredient->recipeCommand.getIngredient()
			.add(ingredientToIngredientCommand.convert(ingredient)));
		}
		
		
		
		
		return recipeCommand;
	}

}
