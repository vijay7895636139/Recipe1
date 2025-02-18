package com.vijay.Recipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.vijay.Recipe.model.Difficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

private Long id;	
private String description;
private Integer prepTime;
private Integer cookTime;
private Integer serving;
private String source;
private String url;	
private String directions;
private Set<IngredientCommand> ingredient=new HashSet<>();
private Difficulty difficulty;
private NotesCommand notes;
private Set<CategoryCommand> category=new HashSet<>();

}
