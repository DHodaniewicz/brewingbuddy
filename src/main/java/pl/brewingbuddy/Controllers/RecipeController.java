package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.BeerStyle;
import pl.brewingbuddy.entities.Malt;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.repositories.BeerStyleRepository;
import pl.brewingbuddy.repositories.MaltRepository;
import pl.brewingbuddy.repositories.RecipeMaltRepository;
import pl.brewingbuddy.repositories.RecipeRepository;
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

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository,
                            BeerStyleRepository beetStyleRepository, MaltRepository maltRepository,
                            RecipeMaltRepository recipeMaltRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.maltRepository = maltRepository;
        this.recipeMaltRepository = recipeMaltRepository;
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
        Recipe recipe = recipeRepository.getById(2L);
        Malt malt = maltRepository.getById(1L);
        RecipeMalt recipeMalt = new RecipeMalt();
        recipeMalt.setRecipe(recipe);
        recipeMalt.setMalt(malt);
        recipeMaltRepository.save(recipeMalt);
        return null;
    }


    @ModelAttribute("availableBeerStyles")
    List<BeerStyle> availableBeerStyles() {
        return beetStyleRepository.findAll();
    }

    @ModelAttribute("availableMalts")
    List<Malt> availableMalts() {
        return maltRepository.findAll();
    }

}
