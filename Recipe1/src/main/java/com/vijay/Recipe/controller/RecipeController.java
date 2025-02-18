package com.vijay.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vijay.Recipe.commands.RecipeCommand;
import com.vijay.Recipe.service.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;
	//private final CategoryService categoryService;
	

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id,Model model)
	{
		//System.out.println(categoryService.getCategory(new Long(id)));
		
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		//model.addAttribute("category", categoryService.getCategory(new Long(id)));
		
		return "recipe/show";
	}
	
	@RequestMapping("recipe/new")
	public String newRecipe(Model model)
	{
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
		
	}
	
	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
		
	}
	
	@PostMapping("recipe")
	public String saveOrupdate(@ModelAttribute RecipeCommand command)
	{
		RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
		
	}
	
	@RequestMapping("recipe/{id}/delete")
	public String delete(@PathVariable Long id)
	{
		System.out.println("at 1");
		try {
	    recipeService.deleteById(id); 
		}catch(Exception e)
		{
			System.out.print(e);
			
		}
		return "redirect:/";
	}
	
	
	
	
	
}
