package com.vijay.Recipe.model;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	@Lob
	private String directions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredient = new HashSet<>();

	@Lob // for large objects
	private Byte[] image;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;


	@Lob
	@OneToOne(cascade = CascadeType.ALL) // cascade->Recipe is the one who own to Notes
	private Notes notes;

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> category = new HashSet<>();

	public void setNotes(Notes notes) {
		if(notes!=null)
		{
		this.notes = notes;
		notes.setRecipe(this);
		}
	}

	public Recipe addIngredient(Ingredient ingredients) {
		ingredients.setRecipe(this);
		this.ingredient.add(ingredients);
		return this;
	}

}
