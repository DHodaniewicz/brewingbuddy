package pl.brewingbuddy.Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.pojo.BasicParamsPojo;
import pl.brewingbuddy.pojo.TestPojo;
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
        try {
            expectedAmountOfBeer = Double.parseDouble((String) jsonObject.get("expectedAmountOfBeer"));
        } catch (JSONException e) {
            return e.getMessage();
        }
        Recipe recipe = recipeRepository.getById(1L);
        recipe.setExpectedAmountOfBeer(expectedAmountOfBeer);
        recipeRepository.save(recipe);
        return expectedAmountOfBeer.toString();
    }

    @PostMapping("/value2")
    public Double restAdd2Attribute(@RequestBody TestPojo testPojo) {
        return testPojo.getExpectedAmountOfBeer();
    }

    @PostMapping("/basicParams")
    public BasicParamsPojo postBasicParams(@RequestBody BasicParamsPojo basicParamsPojo, HttpServletRequest request) {
        //Long recipeId = Long.getLong(request.getSession().getAttribute("recipeId").toString()) ;
        Long recipeId = 1L;
        Recipe recipe = recipeService.setBasicParams(recipeId, basicParamsPojo);
        recipeRepository.save(recipe);
        return basicParamsPojo;
    }

}
