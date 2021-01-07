package com.vijay.Recipe.service;


import java.util.Set;

import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.model.Recipe;

public interface RecipeService  {

	Set<Recipe> getRecipes();
	Recipe findById(Long l);
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	RecipeCommand findCommandById(Long valueOf);
	void deleteById(Long long1);

}
