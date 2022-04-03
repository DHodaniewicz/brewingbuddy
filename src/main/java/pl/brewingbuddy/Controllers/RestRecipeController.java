package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.converters.RecipeHopConverter;
import pl.brewingbuddy.converters.RecipeMaltConverter;
import pl.brewingbuddy.entities.*;
import pl.brewingbuddy.pojo.*;
import pl.brewingbuddy.repositories.*;
import pl.brewingbuddy.servicess.RecipeMaltService;
import pl.brewingbuddy.servicess.RecipeService;

import javax.servlet.http.HttpSession;

@RequestMapping("/recipe")
@RestController
public class RestRecipeController {
    RecipeService recipeService;
    RecipeMaltService recipeMaltService;
    RecipeRepository recipeRepository;
    BeerStyleRepository beetStyleRepository;
    MaltRepository maltRepository;
    RecipeMaltRepository recipeMaltRepository;
    HopRepository hopRepository;
    RecipeHopRepository recipeHopRepository;
    RecipeHopConverter recipeHopConverter;
    RecipeMaltConverter recipeMaltConverter;

    @Autowired
    public RestRecipeController(RecipeService recipeService, RecipeMaltService recipeMaltService, RecipeRepository recipeRepository, BeerStyleRepository beetStyleRepository, MaltRepository maltRepository, RecipeMaltRepository recipeMaltRepository, HopRepository hopRepository, RecipeHopRepository recipeHopRepository, RecipeHopConverter recipeHopConverter, RecipeMaltConverter recipeMaltConverter) {
        this.recipeService = recipeService;
        this.recipeMaltService = recipeMaltService;
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.maltRepository = maltRepository;
        this.recipeMaltRepository = recipeMaltRepository;
        this.hopRepository = hopRepository;
        this.recipeHopRepository = recipeHopRepository;
        this.recipeHopConverter = recipeHopConverter;
        this.recipeMaltConverter = recipeMaltConverter;
    }

    @PostMapping("/basicParams")
    public CalculatedParamsPojo postBasicParams(@RequestBody BasicParamsPojo basicParamsPojo, HttpSession session) {
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 4L;
        Recipe recipe = recipeRepository.getById(recipeId);
        recipe = recipeService.setBasicParams(recipe, basicParamsPojo);
        recipe = recipeService.calculateRecipe(recipe);
        recipe = recipeRepository.save(recipe);
        CalculatedParamsPojo calculatedParamsPojo = recipeService.getCalculatedParams(recipe);
        return calculatedParamsPojo;
    }



}
