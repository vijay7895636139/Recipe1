package com.vijay.Recipe.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vijay.Recipe.commands.IngredientCommand;
import com.vijay.Recipe.converters.IngredientCommandToIngredient;
import com.vijay.Recipe.converters.IngredientToIngredientCommand;
import com.vijay.Recipe.model.Ingredient;
import com.vijay.Recipe.model.Recipe;
import com.vijay.Recipe.repositories.RecipeRepository;
import com.vijay.Recipe.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService{

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	
	
	
	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, 
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}


	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {

		Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);
		
		if(!recipeOptional.isPresent())
		{
			throw new RuntimeException("not found");
		}
		
		Recipe recipe=recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional=recipe.getIngredient().stream()
				.filter(ingredient-> ingredient.getId().equals(id))
				.map(ingredient->ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent())
			
		{
			throw new RuntimeException("not found");
		}
		return ingredientCommandOptional.get();
	}


	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {

		Optional<Recipe> recipeOptional=recipeRepository.findById(command.getRecipeId());
		
		if(!recipeOptional.isPresent())
		{
			return new IngredientCommand();
		}else {
			Recipe recipe=recipeOptional.get();
			
			Optional<Ingredient> ingredientOptional=recipe.getIngredient()
					.stream().filter(ingredient->ingredient.getId().equals(command.getId()))
					.findFirst();
			        
			if(ingredientOptional.isPresent())
			{
				Ingredient ingredientFound=ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(()->new RuntimeException("UOM not found")));
			}
			else {
				
				Ingredient ingredient=ingredientCommandToIngredient.convert(command);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
				/* recipe.addIngredient(ingredientCommandToIngredient.convert(command)); */
			}
			Recipe savedRecipe=recipeRepository.save(recipe);
			
			Optional<Ingredient> savedIngredientOptional=savedRecipe.getIngredient().stream()
					.filter(recipeIngredient->recipeIngredient.getId().equals(command.getId())).findFirst();
			
			if(!savedIngredientOptional.isPresent())
			{
				savedIngredientOptional=savedRecipe.getIngredient().stream()
						.filter(recipeIngredient->recipeIngredient.getDescription().equals(command.getDescription()))
						.filter(recipeIngredient->recipeIngredient.getAmount().equals(command.getAmount()))
						.filter(recipeIngredient->recipeIngredient.getUom().getId().equals(command.getUom().getId()))
						.findFirst();
						
			}
			
			/*
			 * return ingredientToIngredientCommand.convert(savedRecipe.getIngredient()
			 * .stream().filter(recipeIngredient->recipeIngredient.getId().equals(command.
			 * getId())) .findFirst().get());
			 */
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}	
	}
}
