package com.vijay.Recipe.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vijay.Recipe.model.Recipe;
import com.vijay.Recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
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
	
	
	

}