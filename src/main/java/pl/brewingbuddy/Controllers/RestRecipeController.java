package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    public RestRecipeController(RecipeService recipeService, RecipeMaltService recipeMaltService, RecipeRepository recipeRepository, BeerStyleRepository beetStyleRepository, MaltRepository maltRepository, RecipeMaltRepository recipeMaltRepository, HopRepository hopRepository, RecipeHopRepository recipeHopRepository) {
        this.recipeService = recipeService;
        this.recipeMaltService = recipeMaltService;
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.maltRepository = maltRepository;
        this.recipeMaltRepository = recipeMaltRepository;
        this.hopRepository = hopRepository;
        this.recipeHopRepository = recipeHopRepository;
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

    @PostMapping("/add/malt")
    public RecipeMaltPojo addMaltToRecipe(@RequestBody RecipeMaltPojo recipeMaltPojo, HttpSession session) {

        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 4L;
        Malt malt = maltRepository.getById(recipeMaltPojo.getMaltId());
        Recipe recipe = recipeRepository.getById(recipeId);

        //RecipeMalt recipeMalt = recipeMaltRepository.findRecipeMaltByRecipeAndMalt(recipe, malt);
        //if (recipeMalt == null) {
        //    recipeMalt = new RecipeMalt();
        //} else {
        //    recipeMaltRepository.delete(recipeMalt);
        //}

        RecipeMalt recipeMalt = new RecipeMalt();

        Double amount = recipeMaltPojo.getAmount();
        recipeMalt.setRecipe(recipe);
        recipeMalt.setMalt(malt);
        recipeMalt.setAmount(amount);
        recipeMaltRepository.save(recipeMalt).getId();
        return recipeMaltPojo;
    }

    @PostMapping("/add/hop")
    public RecipeHopPojo addHopToRecipe(@RequestBody RecipeHopPojo recipeHopPojo, HttpSession session){
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 1L;
        Recipe recipe = recipeRepository.getById(recipeId);
        Hop hop = hopRepository.getById(recipeHopPojo.getHopId());
        Integer timeOfBoiling = recipeHopPojo.getTimeOfBoiling();
        Integer amount = recipeHopPojo.getAmount();

        RecipeHop recipeHop = new RecipeHop();
        recipeHop.setRecipe(recipe);
        recipeHop.setHop(hop);
        recipeHop.setTimeOfBoiling(timeOfBoiling);
        recipeHop.setAmount(amount);

        recipeHopRepository.save(recipeHop);

        return recipeHopPojo;
    }



}
