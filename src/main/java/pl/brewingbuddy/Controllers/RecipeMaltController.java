package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brewingbuddy.converters.RecipeMaltConverter;
import pl.brewingbuddy.entities.Malt;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.pojo.RecipeMaltPojo;
import pl.brewingbuddy.repositories.MaltRepository;
import pl.brewingbuddy.repositories.RecipeMaltRepository;
import pl.brewingbuddy.repositories.RecipeRepository;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/recipe/malt")
public class RecipeMaltController {
    MaltRepository maltRepository;
    RecipeRepository recipeRepository;
    RecipeMaltRepository recipeMaltRepository;
    RecipeMaltConverter recipeMaltConverter;

    @Autowired
    public RecipeMaltController(MaltRepository maltRepository, RecipeRepository recipeRepository, RecipeMaltRepository recipeMaltRepository, RecipeMaltConverter recipeMaltConverter) {
        this.maltRepository = maltRepository;
        this.recipeRepository = recipeRepository;
        this.recipeMaltRepository = recipeMaltRepository;
        this.recipeMaltConverter = recipeMaltConverter;
    }

    @PostMapping("/add")
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
        recipeMalt = recipeMaltRepository.save(recipeMalt);
        recipeMaltPojo = recipeMaltConverter.toPojo(recipeMalt);
        return recipeMaltPojo;
    }
}
