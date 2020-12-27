package com.vijay.Recipe.Bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.vijay.Recipe.model.Category;
import com.vijay.Recipe.model.Difficulty;
import com.vijay.Recipe.model.Ingredient;
import com.vijay.Recipe.model.Notes;
import com.vijay.Recipe.model.Recipe;
import com.vijay.Recipe.model.UnitOfMeasure;
import com.vijay.Recipe.repositories.CategoryRepository;
import com.vijay.Recipe.repositories.RecipeRepository;
import com.vijay.Recipe.repositories.UnitOfMeasureRepository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
   
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureReository;
	private final RecipeRepository recipeRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureReository,
			RecipeRepository recipeRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureReository = unitOfMeasureReository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		recipeRepository.saveAll(getRecipes());
	}


	@SuppressWarnings("restriction")
	private List<Recipe> getRecipes()
	{
		log.debug("loading bootstrap data");
		//---->> IF WE DO NOT ADD DATA THROUGH DATA.SQL(SOURCES) THEN WE CAN DO IT MANUALY AS WE DONE IT BLOW------>>
		/*
		 * UnitOfMeasure uom1=new UnitOfMeasure(); uom1.setDescription("Each");
		 * unitOfMeasureReository.save(uom1);
		 * 
		 * UnitOfMeasure uom2=new UnitOfMeasure(); uom2.setDescription("TableSpoon");
		 * unitOfMeasureReository.save(uom2);
		 * 
		 * UnitOfMeasure uom3=new UnitOfMeasure(); uom3.setDescription("TeaSpoon");
		 * unitOfMeasureReository.save(uom3);
		 * 
		 * UnitOfMeasure uom4=new UnitOfMeasure(); uom4.setDescription("Dash");
		 * unitOfMeasureReository.save(uom4);
		 * 
		 * UnitOfMeasure uom5=new UnitOfMeasure(); uom5.setDescription("Pint");
		 * unitOfMeasureReository.save(uom5);
		 * 
		 * UnitOfMeasure uom6=new UnitOfMeasure(); uom6.setDescription("Cup");
		 * unitOfMeasureReository.save(uom6);
		 * 
		 * Category cat1=new Category(); cat1.setDescription("Indian");
		 * categoryRepository.save(cat1);
		 * 
		 * Category cat2=new Category(); cat2.setDescription("Mexican");
		 * categoryRepository.save(cat2);
		 */
		
		
		List<Recipe> recipes=new ArrayList<>(2);
	
	Optional<UnitOfMeasure> eachUomOptional=unitOfMeasureReository.findByDescription("Each");
	if(!eachUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 1");
	}
	
	Optional<UnitOfMeasure> tableSpoonUomOptional=unitOfMeasureReository.findByDescription("TableSpoon");
	if(!tableSpoonUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 2");
	}
	
	Optional<UnitOfMeasure> teaSpoonUomOptional=unitOfMeasureReository.findByDescription("TeaSpoon");
	if(!teaSpoonUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 3");
	}
	
	Optional<UnitOfMeasure> dashUomOptional=unitOfMeasureReository.findByDescription("Dash");
	if(!dashUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 4");
	}
	
	Optional<UnitOfMeasure> pintUomOptional=unitOfMeasureReository.findByDescription("Pint");
	if(!pintUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 5");
	}
	
	Optional<UnitOfMeasure> cupUomOptional=unitOfMeasureReository.findByDescription("Cup");
	if(!cupUomOptional.isPresent()) {
		throw new RuntimeException("Expected UOM not found 6");
	}
	
	UnitOfMeasure eachUom=eachUomOptional.get();
	UnitOfMeasure tableSpoonUom=tableSpoonUomOptional.get();
	UnitOfMeasure teaSponUom=teaSpoonUomOptional.get();
	UnitOfMeasure dashUom=dashUomOptional.get();
	UnitOfMeasure pintUom=pintUomOptional.get();
	UnitOfMeasure cupUom=cupUomOptional.get();
	
	
	Optional<Category> indianCategoryOptional=categoryRepository.findByDescription("Indian");
	if(!indianCategoryOptional.isPresent())
	{
		throw new RuntimeException("Category is not found");
	}
	
	Optional<Category> mexicanCategoryOptional=categoryRepository.findByDescription("Mexican");
	if(!mexicanCategoryOptional.isPresent())
	{
		throw new RuntimeException("Category is not fourd"); 
	}
	
	Category indianCategory=indianCategoryOptional.get();
	Category mexicanCategory=mexicanCategoryOptional.get();
	

	//Yummy Guac
	Recipe guacRecipe=new Recipe();
	guacRecipe.setDescription("Perfect Guacmole");
	guacRecipe.setCookTime(10);
	guacRecipe.setPrepTime(10);
	//guacRecipe.setServing(5);
	guacRecipe.setDifficulty(Difficulty.EASY);
	guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with "
			+ "a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."+"\n"
					+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"+"\n"
			   +"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide "
			   + "some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n"
							+ "\r\n"
							+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So,"
							+ " start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n"
							+ "\r\n"
							+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this "
							+ "recipe and adjust to your taste.\r\n"
							+ "\r\n"
							+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before"
							+ " serving."+"\n"+
							"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press "
							+ "down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole"
							+ " brown.) Refrigerate until ready to serve.");
	
	Notes guacNotes=new Notes();
	guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados."+"\n"+
	"feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican "
	+ "guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!"+
	"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? "
	+ "It tastes great.");
	
	guacNotes.setRecipe(guacRecipe);
	guacRecipe.setNotes(guacNotes);
	
	
	guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2),eachUom));
	guacRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal(5), teaSponUom));
	guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
	guacRecipe.addIngredient(new Ingredient("minced red onion or thinly green onion", new BigDecimal(2),tableSpoonUom));
	guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2),eachUom));
	guacRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2),tableSpoonUom));
	guacRecipe.addIngredient(new Ingredient("freshly granted black pepper", new BigDecimal(2), dashUom));
	guacRecipe.addIngredient(new Ingredient("ripe tomato, seed and pulp removed, chopped", new BigDecimal(5),eachUom));
	
	guacRecipe.getCategory().add(indianCategory);
	guacRecipe.getCategory().add(mexicanCategory);
	
	recipes.add(guacRecipe);
	
	Recipe tacosRecipe=new Recipe();
	tacosRecipe.setDescription("spicy grilled chiken taco");
	tacosRecipe.setCookTime(9);
	tacosRecipe.setPrepTime(10);
	tacosRecipe.setDifficulty(Difficulty.MODERATE);
	//HAVE TO DO IT LATER
	tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\\n\" +\r\n"
			+ "                \"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, "
			+ "garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all "
			+ "over.\\n\" +\r\n"
			+ "                \"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\\n\" +\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest"
			+ " part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\\n\" +\r\n"
			+ "                \"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you "
			+ "see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\\n\" +\r\n"
			+ "                \"Wrap warmed tortillas in a tea towel to keep them warm until serving.\\n\" +\r\n"
			+ "                \"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with "
			+ "chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\\n\" "
			+ "+\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");
	
	Notes tacoNotes=new Notes();
	tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\\n\" +\r\n"
			+ "                \"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can "
			+ "always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\\n\" +\r\n"
			+ "                \"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\\n\" +\r\n"
			+ "                \"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice "
			+ "while the grill is heating. You can also use this time to prepare the taco toppings.\\n\" +\r\n"
			+ "                \"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in."
			+ " The whole meal comes together in about 30 minutes!\\n\" +\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"\\n\" +\r\n"
			+ "                \"Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");//LATER
	
	tacoNotes.setRecipe(tacosRecipe);
	tacosRecipe.setNotes(tacoNotes);
	
	tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
	tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSponUom));
	tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1),teaSponUom));
	tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1),teaSponUom));
	tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(5), teaSponUom));
	tacosRecipe.addIngredient(new Ingredient("Clover of Garlic Chopped", new BigDecimal(1),eachUom));
	tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1),tableSpoonUom));
	tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
	tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom));
	tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
	tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8),eachUom));
	tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3),cupUom));
	tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
	tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
	tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(5), pintUom));
	tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(25), eachUom));
	tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4),eachUom));
	tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
	tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4),  eachUom));
	
	tacosRecipe.getCategory().add(indianCategory);
	tacosRecipe.getCategory().add(mexicanCategory);
	recipes.add(tacosRecipe);

	return recipes;
	
	
	
	
	
	}
	
}
