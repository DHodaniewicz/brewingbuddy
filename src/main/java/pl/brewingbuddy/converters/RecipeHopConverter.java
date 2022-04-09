package pl.brewingbuddy.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.pojo.RecipeHopPojo;
import pl.brewingbuddy.repositories.HopRepository;
import pl.brewingbuddy.repositories.RecipeHopRepository;
import pl.brewingbuddy.repositories.RecipeRepository;

@Component
public class RecipeHopConverter {
    RecipeHopRepository recipeHopRepository;
    HopRepository hopRepository;
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeHopConverter(RecipeHopRepository recipeHopRepository, HopRepository hopRepository, RecipeRepository recipeRepository) {
        this.recipeHopRepository = recipeHopRepository;
        this.hopRepository = hopRepository;
        this.recipeRepository = recipeRepository;
    }

    public RecipeHopPojo toPojo (RecipeHop recipeHop) {
        RecipeHopPojo recipeHopPojo = new RecipeHopPojo();
        recipeHopPojo.setId(recipeHop.getId());
        recipeHopPojo.setRecipeId(recipeHop.getRecipe().getId());
        recipeHopPojo.setHopId(recipeHop.getHop().getId());
        recipeHopPojo.setAmount(recipeHop.getAmount());
        recipeHopPojo.setTimeOfBoiling(recipeHop.getTimeOfBoiling());
        recipeHopPojo.setHopName(recipeHop.getHop().getName());
        return recipeHopPojo;
    }

    public RecipeHop toEntity (RecipeHopPojo recipeHopPojo) {
        RecipeHop recipeHop = recipeHopRepository.getById(recipeHopPojo.getId());
        recipeHop.setHop(hopRepository.getById(recipeHopPojo.getHopId()));
        recipeHop.setAmount(recipeHopPojo.getAmount());
        recipeHop.setTimeOfBoiling(recipeHopPojo.getTimeOfBoiling());
        return recipeHop;
    }
}
