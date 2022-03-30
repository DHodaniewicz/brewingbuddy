package pl.brewingbuddy.Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.*;
import pl.brewingbuddy.pojo.BasicParamsPojo;
import pl.brewingbuddy.pojo.RecipeHopPojo;
import pl.brewingbuddy.pojo.RecipeMaltPojo;
import pl.brewingbuddy.pojo.TestPojo;
import pl.brewingbuddy.repositories.*;
import pl.brewingbuddy.servicess.RecipeMaltService;
import pl.brewingbuddy.servicess.RecipeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/recipe")
@RestController
public class RestRecipeController {
    RecipeService recipeService;
    RecipeMaltService recipeMaltService;
    RecipeRepository recipeRepository;
    BeetStyleRepository beetStyleRepository;
    MaltRepository maltRepository;
    RecipeMaltRepository recipeMaltRepository;
    HopRepository hopRepository;
    RecipeHopRepository recipeHopRepository;

    @Autowired
    public RestRecipeController(RecipeService recipeService, RecipeMaltService recipeMaltService, RecipeRepository recipeRepository, BeetStyleRepository beetStyleRepository, MaltRepository maltRepository, RecipeMaltRepository recipeMaltRepository, HopRepository hopRepository, RecipeHopRepository recipeHopRepository) {
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
    public BasicParamsPojo postBasicParams(@RequestBody BasicParamsPojo basicParamsPojo, HttpSession session) {
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 1L;
        Recipe recipe = recipeService.setBasicParams(recipeId, basicParamsPojo);
        recipeRepository.save(recipe);
        return basicParamsPojo;
    }

    @PostMapping("/add/malt")
    public RecipeMaltPojo addMaltToRecipe(@RequestBody RecipeMaltPojo recipeMaltPojo, HttpSession session) {

        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 1L;
        Malt malt = maltRepository.getById(recipeMaltPojo.getMaltId());
        Recipe recipe = recipeRepository.getById(recipeId);

        //RecipeMalt recipeMalt = recipeMaltRepository.findRecipeMaltByRecipeAndMalt(recipe, malt);
        //if (recipeMalt == null) {
        //    recipeMalt = new RecipeMalt();
        //} else {
        //    recipeMaltRepository.delete(recipeMalt);
        //}

        RecipeMalt recipeMalt = new RecipeMalt();

        Integer amount = recipeMaltPojo.getAmount();
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
