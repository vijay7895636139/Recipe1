package com.vijay.Recipe.service;

import com.vijay.Recipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long id);

	IngredientCommand saveIngredientCommand(IngredientCommand command);
}
