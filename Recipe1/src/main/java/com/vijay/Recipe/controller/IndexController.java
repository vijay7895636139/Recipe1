package com.vijay.Recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vijay.Recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	// @Autowired
	private final RecipeService recipeService;

	 
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@RequestMapping({ "/", "", "/index" })
	public String recipe(Model model) {
		log.debug("getting index page");

		model.addAttribute("recipes", recipeService.getRecipes());

		return "index";
	}
}