package pl.brewingbuddy.Controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.Malt;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.repositories.BeetStyleRepository;
import pl.brewingbuddy.repositories.MaltRepository;
import pl.brewingbuddy.repositories.RecipeMaltRepository;
import pl.brewingbuddy.repositories.RecipeRepository;
import pl.brewingbuddy.servicess.RecipeService;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/recipe")
@RestController
public class RestRecipeController {
    RecipeService recipeService;
    RecipeRepository recipeRepository;
    BeetStyleRepository beetStyleRepository;
    MaltRepository maltRepository;
    RecipeMaltRepository recipeMaltRepository;

    public RestRecipeController(RecipeService recipeService, RecipeRepository recipeRepository,
                                BeetStyleRepository beetStyleRepository, MaltRepository maltRepository,
                                RecipeMaltRepository recipeMaltRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.maltRepository = maltRepository;
        this.recipeMaltRepository = recipeMaltRepository;
    }

    @PostMapping("/value")
    public String restAdd1Attribute(@RequestBody String data, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(data);
        Double expectedAmountOfBeer;
        if(jsonObject.get("expectedAmountOfBeer") != null) {
            Recipe recipe = recipeRepository.getById(1L);
            recipe.setExpectedAmountOfBeer(Double.parseDouble((String) jsonObject.get("expectedAmountOfBeer")));
            recipeRepository.save(recipe);
        }
        return "dupa";
    }

}
