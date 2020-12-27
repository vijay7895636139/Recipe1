package com.vijay.Recipe.repositories;


import org.springframework.data.repository.CrudRepository;

import com.vijay.Recipe.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
