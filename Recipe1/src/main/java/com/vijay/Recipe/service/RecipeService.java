package com.vijay.Recipe.service;


import java.util.Set;

import com.vijay.Recipe.model.Recipe;

public interface RecipeService  {

	Set<Recipe> getRecipes();
	Recipe findById(Long l);
}
