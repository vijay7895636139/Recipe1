package com.vijay.Recipe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private String recipeNotes;

	@OneToOne
	private Recipe recipe;

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getRecipeNotes() { return recipeNotes; }
	 * 
	 * public void setRecipeNotes(String recipeNotes) { this.recipeNotes =
	 * recipeNotes; }
	 * 
	 * public Recipe getRecipe() { return recipe; }
	 * 
	 * public void setRecipe(Recipe recipe) { this.recipe = recipe; }
	 */

}