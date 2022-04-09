package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.*;
import pl.brewingbuddy.repositories.*;
import pl.brewingbuddy.servicess.RecipeService;

import javax.servlet.http.HttpServletRequest;
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
    public String viewAddRecipe(HttpServletRequest request) {
        Recipe recipe = new Recipe();
        recipe = recipeRepository.save(recipe);
        request.getSession().setAttribute("recipeId", recipe.getId());
        return "addRecipe";
    }


    @GetMapping("/test")
    public String test() {
        return "addRecipe";
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
