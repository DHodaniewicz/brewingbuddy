package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.converters.RecipeHopConverter;
import pl.brewingbuddy.entities.Hop;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.pojo.RecipeHopPojo;
import pl.brewingbuddy.repositories.HopRepository;
import pl.brewingbuddy.repositories.RecipeHopRepository;
import pl.brewingbuddy.repositories.RecipeRepository;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/recipe/hop")
public class RecipeHopController {
    RecipeRepository recipeRepository;
    HopRepository hopRepository;
    RecipeHopRepository recipeHopRepository;
    RecipeHopConverter recipeHopConverter;

    public RecipeHopController(RecipeRepository recipeRepository, HopRepository hopRepository, RecipeHopRepository recipeHopRepository, RecipeHopConverter recipeHopConverter) {
        this.recipeRepository = recipeRepository;
        this.hopRepository = hopRepository;
        this.recipeHopRepository = recipeHopRepository;
        this.recipeHopConverter = recipeHopConverter;
    }

    @PostMapping("/add")
    public RecipeHopPojo addHopToRecipe(@RequestBody RecipeHopPojo recipeHopPojo, HttpSession session) {
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 4L;
        Recipe recipe = recipeRepository.getById(recipeId);
        Hop hop = hopRepository.getById(recipeHopPojo.getHopId());
        Integer timeOfBoiling = recipeHopPojo.getTimeOfBoiling();
        Double amount = recipeHopPojo.getAmount();

        RecipeHop recipeHop = new RecipeHop();
        recipeHop.setRecipe(recipe);
        recipeHop.setHop(hop);
        recipeHop.setTimeOfBoiling(timeOfBoiling);
        recipeHop.setAmount(amount);

        recipeHop = recipeHopRepository.save(recipeHop);

        recipeHopPojo = recipeHopConverter.toPojo(recipeHop);

        return recipeHopPojo;
    }

    @PutMapping("/update")
    public RecipeHopPojo updateRecipeHop(@RequestBody RecipeHopPojo recipeHopPojo, HttpSession session) {
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 4L;
        RecipeHop recipeHop = recipeHopConverter.toEntity(recipeHopPojo);
        recipeHop = recipeHopRepository.save(recipeHop);
        recipeHopPojo = recipeHopConverter.toPojo(recipeHop);
        return recipeHopPojo;
    }

    @DeleteMapping("/delete")
    public void deleteRecipeHop(@RequestBody RecipeHopPojo recipeHopPojo) {
        RecipeHop recipeHop = recipeHopConverter.toEntity(recipeHopPojo);
        recipeHopRepository.delete(recipeHop);
    }

    @GetMapping("/all")
    public Set<RecipeHopPojo> getAllRecipeHop() {
        Set<RecipeHop> allRecipeHop = new HashSet<>();
        //Long recipeId = Long.parseLong(session.getAttribute("recipeId").toString());
        Long recipeId = 4L;
        Recipe recipe = recipeRepository.getById(recipeId);
        allRecipeHop = recipeHopRepository.findAllByRecipe(recipe);
        Set<RecipeHopPojo> allRecipeHopPojo = new HashSet<>();

        for(RecipeHop recipeHop : allRecipeHop) {
            allRecipeHopPojo.add(recipeHopConverter.toPojo(recipeHop));
        }
        return allRecipeHopPojo;
    }

}
