package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.*;
import pl.brewingbuddy.repositories.*;
import pl.brewingbuddy.servicess.RecipeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.GroupSequence;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    RecipeService recipeService;
    RecipeRepository recipeRepository;
    BeerStyleRepository beetStyleRepository;
    MaltRepository maltRepository;
    RecipeMaltRepository recipeMaltRepository;
    YeastRepository yeastRepository;
    HopRepository hopRepository;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository, BeerStyleRepository beetStyleRepository, MaltRepository maltRepository, RecipeMaltRepository recipeMaltRepository, YeastRepository yeastRepository, HopRepository hopRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.maltRepository = maltRepository;
        this.recipeMaltRepository = recipeMaltRepository;
        this.yeastRepository = yeastRepository;
        this.hopRepository = hopRepository;
    }

    @GetMapping("/add")
    public String viewAddRecipe(HttpServletRequest request, Model model) {
        Recipe recipe = new Recipe();
        recipe = recipeRepository.save(recipe);
        recipe = recipeService.setBasicParamsOfCreatedRecipe(recipe);
        model.addAttribute("newRecipe", recipe);
        request.getSession().setAttribute("recipeId", recipe.getId());
        return "addRecipe";
    }

    @GetMapping("/all")
    public String getAllRecipes(Model model) {
        List<Recipe> allRecipes = recipeRepository.findAll();
        model.addAttribute("allRecipes", allRecipes);
        return "allRecipes";
    }

    @GetMapping("/all/filter")
    public String getFilteredRecipes(@RequestParam Long beerStyleId, Model model) {
        List <Recipe> filteredRecipes = recipeRepository.findAllByBeerStyle(beetStyleRepository.getById(beerStyleId));
        model.addAttribute("allRecipes", filteredRecipes);
        return "allRecipes";
    }

    @GetMapping("/details/{id}")
    public String getRecipeDetails(@PathVariable Long id, Model model) {
        model.addAttribute("recipeDetails", recipeRepository.getById(id));
        return "recipeDetails";
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable Long id, Model model, HttpServletRequest request) {
        request.getSession().setAttribute("recipeId", id);
        Recipe recipe = recipeRepository.getById(id);
        model.addAttribute("newRecipe", recipe);
        return "updateRecipe";
    }


    @ModelAttribute("availableBeerStyles")
    List<BeerStyle> availableBeerStyles() {
        return beetStyleRepository.findAll();
    }

    @ModelAttribute("availableYeast")
    List<Yeast> availableYeast() {
        return yeastRepository.findAll();
    }

    @ModelAttribute("availableMalts")
    List<Malt> availableMalts() {
        return maltRepository.findAll();
    }

    @ModelAttribute("availableHops")
    List<Hop> availableHops() {
        return hopRepository.findAll();
    }

}
