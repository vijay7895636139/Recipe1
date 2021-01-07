package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CategoryCommandToCategory categoryCommandToCategory;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final NotesCommandToNotes notesCommandToNotes;


	public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
			IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes) {
		super();
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.notesCommandToNotes = notesCommandToNotes;
	}

	@Override
	@Nullable
	@Synchronized
	public Recipe convert(RecipeCommand source) {

		if(source==null)
		{
			return null;
		}
		
		final Recipe recipe=new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setCookTime(source.getCookTime());
		recipe.setServing(source.getServing());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setDirections(source.getDirections());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
		
		if(source.getCategory()!=null && source.getCategory().size()>0)
		{
			source.getCategory()
			.forEach(category->recipe.getCategory()
			.add(categoryCommandToCategory.convert(category)));
		}
		
		if(source.getIngredient()!=null && source.getIngredient().size()>0)
		{
			source.getIngredient()
			.forEach(ingredient->recipe.getIngredient()
			.add(ingredientCommandToIngredient.convert(ingredient)));
		}
		
		return recipe;
	}

}
