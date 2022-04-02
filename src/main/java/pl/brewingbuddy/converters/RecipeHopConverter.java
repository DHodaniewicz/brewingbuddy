package pl.brewingbuddy.converters;

import org.springframework.stereotype.Component;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.pojo.RecipeHopPojo;

@Component
public class RecipeHopConverter {
    public RecipeHopPojo toPojo (RecipeHop recipeHop) {
        RecipeHopPojo recipeHopPojo = new RecipeHopPojo();
        recipeHopPojo.setId(recipeHop.getId());
        recipeHopPojo.setRecipeId(recipeHop.getRecipe().getId());
        recipeHopPojo.setHopId(recipeHop.getHop().getId());
        recipeHopPojo.setAmount(recipeHop.getAmount());
        recipeHopPojo.setTimeOfBoiling(recipeHop.getTimeOfBoiling());
        return recipeHopPojo;
    }
}
