package com.vijay.Recipe.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vijay.Recipe.commands.IngredientCommand;
import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.commands.UnitOfMeasureCommand;
import com.vijay.Recipe.service.IngredientService;
import com.vijay.Recipe.service.RecipeService;
import com.vijay.Recipe.service.UnitOfMeasureService;

@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
	
	
	@RequestMapping("/recipe/{id}/list")
	public String showingredient(@PathVariable Long id, Model model)
	{
		System.out.println("Now you are here");
		model.addAttribute("recipe", recipeService.findCommandById(id));
		
		return "recipe/ingredient";
		
	}
	
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
	public String findbyIngredientId(@PathVariable Long recipeId,
									 @PathVariable Long id, Model model)
	{
		//System.out.println("hi point 1 recipe id :"+recipeId+"ingredient id :"+iid);
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
		return "/recipe/ingredient/show";
		
	}
	
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newRecipe(@PathVariable Long recipeId, Model model)
	{
		RecipeCommand recipeCommand=recipeService.findCommandById(recipeId);
		IngredientCommand ingredientCommand= new IngredientCommand();
		ingredientCommand.setRecipeId(recipeId);
		model.addAttribute("ingredient",ingredientCommand);
		ingredientCommand.setUom(new UnitOfMeasureCommand());
		model.addAttribute("uomList", unitOfMeasureService.ListAllUoms());
		
		
		return "recipe/ingredient/ingredientform";
		
	}
	
	
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model)
	{
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
		model.addAttribute("uomList", unitOfMeasureService.ListAllUoms());

		return "recipe/ingredient/ingredientform";
		
	}
	
	@RequestMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand command)
	{
	  IngredientCommand savedCommand=ingredientService.saveIngredientCommand(command);
		return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
		
	}
	
	
}
