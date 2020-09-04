package com.georgidinov.recipesapp.bootstrap;

import com.georgidinov.recipesapp.domain.Category;
import com.georgidinov.recipesapp.domain.Difficulty;
import com.georgidinov.recipesapp.domain.Ingredient;
import com.georgidinov.recipesapp.domain.Notes;
import com.georgidinov.recipesapp.domain.Recipe;
import com.georgidinov.recipesapp.repositories.CategoryRepository;
import com.georgidinov.recipesapp.repositories.RecipeRepository;
import com.georgidinov.recipesapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

    //== fields ==
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    //== constructors ==
    @Autowired
    public DataLoader(RecipeRepository recipeRepository,
                      CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }//end of constructor


    //== public methods ==
    @Override
    //@Transactional
    public void run(String... args) throws Exception {
        this.loadData();
        log.debug("Loading bootstrap data...");
    }//end of method run


    //== private methods ==
    private void loadData() {
        //==  Guacamole Recipe Ingredients ==
        Set<Ingredient> guacamoleIngredients = new HashSet<>();
//        2 ripe avocados
        Ingredient ripeAvocado = new Ingredient();
        ripeAvocado.setDescription("Ripe Avocado");
        ripeAvocado.setAmount(new BigDecimal("2"));
        ripeAvocado.setUom(this.unitOfMeasureRepository.findByDescription("Count").get());
        guacamoleIngredients.add(ripeAvocado);
//        1/4 teaspoon of salt, more to taste
        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(new BigDecimal("0.25")); // has to be 0.25
        salt.setUom(this.unitOfMeasureRepository.findByDescription("Teaspoon").get());
        guacamoleIngredients.add(salt);
//        1 tablespoon fresh lime juice or lemon juice
        Ingredient lemonJuice = new Ingredient();
        lemonJuice.setDescription("Lemon Juice");
        lemonJuice.setAmount(new BigDecimal("1"));
        lemonJuice.setUom(this.unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(lemonJuice);
//        2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion
        Ingredient redOnion = new Ingredient();
        redOnion.setDescription("Red Onion");
        redOnion.setAmount(new BigDecimal("2"));
        redOnion.setUom(this.unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(redOnion);
//        1-2 serrano chiles, stems and seeds removed, minced
        Ingredient chillies = new Ingredient();
        chillies.setDescription("Serrano Chilies");
        chillies.setAmount(new BigDecimal("2"));
        chillies.setUom(this.unitOfMeasureRepository.findByDescription("Count").get());
        guacamoleIngredients.add(chillies);
//        2 tablespoons cilantro (leaves and tender stems), finely chopped
        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("Cilantro");
        cilantro.setAmount(new BigDecimal("2"));
        cilantro.setUom(this.unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(cilantro);
//        A dash of freshly grated black pepper
        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("Black Pepper");
        blackPepper.setAmount(new BigDecimal("1"));
        blackPepper.setUom(this.unitOfMeasureRepository.findByDescription("Dash").get());
        guacamoleIngredients.add(blackPepper);
//        1/2 ripe tomato, seeds and pulp removed, chopped
        Ingredient tomato = new Ingredient();
        tomato.setDescription("Tomato");
        tomato.setAmount(new BigDecimal("0.5"));
        tomato.setUom(this.unitOfMeasureRepository.findByDescription("Count").get());
        guacamoleIngredients.add(tomato);
//        Red radishes or jicama, to garnish
        Ingredient radishes = new Ingredient();
        radishes.setDescription("Radishes or Jicama");
        radishes.setAmount(new BigDecimal("1"));
        radishes.setUom(this.unitOfMeasureRepository.findByDescription("Bunch").get());
        guacamoleIngredients.add(radishes);
//        Tortilla chips, to serve
        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setDescription("Tortilla Chips");
        tortillaChips.setAmount(new BigDecimal("1"));
        tortillaChips.setUom(this.unitOfMeasureRepository.findByDescription("Bunch").get());
        guacamoleIngredients.add(tortillaChips);
        //== End Of Ingredient List For The Guacamole Recipe ==


        //== Creating and setting up the the Recipe Object
        Recipe guacamoleRecipe = new Recipe();
        String description = "Perfect Guacamole";
        guacamoleRecipe.setDescription(description);
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(10);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        for (Ingredient ingredient : guacamoleIngredients) {
            ingredient.setRecipe(guacamoleRecipe);
        }
        guacamoleRecipe.setIngredients(guacamoleIngredients);
        //== Guacamole notes ==
        String guacamoleNotes = "Once you have basic guacamole down, " +
                "feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. " +
                "One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). " +
                "You can get creative with homemade guacamole!\n" +
                "\n" +
                "    Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. " +
                "Don’t let the lack of availability of other ingredients stop you from making guacamole." +
                "\n" +
                "    Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados." +
                "\n" +
                "    Don’t have enough avocados? To extend a limited supply of avocados, " +
                "add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.";

        Notes notes = new Notes();
        notes.setRecipe(guacamoleRecipe);
        notes.setRecipeNotes(guacamoleNotes);
        guacamoleRecipe.setNotes(notes);

        //== Guacamole Categories
        Set<Category> guacamoleCategories = new HashSet<>();
        guacamoleCategories.add(this.categoryRepository.findByDescription("Fast Food").get());
        guacamoleCategories.add(this.categoryRepository.findByDescription("Mexican").get());
        guacamoleRecipe.setCategories(guacamoleCategories);

        //== Guacamole Directions ==
        String directions = "" +
                "                1 Cut the avocado, remove flesh: Cut the avocados in half." +
                "                Remove the pit. Score the inside of the avocado with a blunt knife" +
                "                and scoop out the flesh with a spoon." +
                "                (See How to Cut and Peel an Avocado.) Place in a bowl." + "\n" +
                "                2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it!" +
                "                The guacamole should be a little chunky.)" + "\n" +
                "                3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice." +
                "                The acid in the lime juice will provide some balance to the richness of the avocado" +
                "                and will help delay the avocados from turning brown." +
                "                Add the chopped onion, cilantro, black pepper, and chiles." +
                "                Chili peppers vary individually in their hotness." +
                "                So, start with a half of one chili pepper and add to the guacamole " +
                "                to your desired degree of hotness." +
                "                Remember that much of this is done to taste because of the" +
                "                variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "                Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole," +
                "                add it just before serving." + "\n" +
                "                4 Serve: Serve immediately, or if making a few hours ahead," +
                "                place plastic wrap on the surface of the guacamole and press down to cover it" +
                "                and to prevent air reaching it." +
                "                (The oxygen in the air causes oxidation which will turn the guacamole brown.)" +
                "                Refrigerate until ready to serve.";
        guacamoleRecipe.setDirections(directions);
        //== Persist the Recipe
        this.recipeRepository.save(guacamoleRecipe);

    }//end of method loadData

}//end of class DataLoader
