package com.vijay.Recipe.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.converters.RecipeCommandToRecipe;
import com.vijay.Recipe.converters.RecipeToRecipeCommand;
import com.vijay.Recipe.model.Recipe;
import com.vijay.Recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("hello");
		
		Set<Recipe> recipeSet=new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long l) {
		
		Optional<Recipe> recipeid= recipeRepository.findById(l);
		if(!recipeid.isPresent())
		{
		    throw new RuntimeException("id not found");	
		}
		return recipeid.get();
	}
	
	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l)
	{
		return recipeToRecipeCommand.convert(findById(l));
	}
	

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		
		Recipe detachedRecipe=recipeCommandToRecipe.convert(command);
		Recipe savedRecipe=recipeRepository.save(detachedRecipe);
		
		
		
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	public void deleteById(Long long1) {
		recipeRepository. deleteById(long1);
		
	}


	
	
	

}