package pl.brewingbuddy.servicess;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.pojo.BasicParamsPojo;
import pl.brewingbuddy.repositories.BeetStyleRepository;
import pl.brewingbuddy.repositories.RecipeRepository;

@Getter
@Setter
@NoArgsConstructor
@Service
public class RecipeService {
    RecipeRepository recipeRepository;
    BeetStyleRepository beetStyleRepository;

@Autowired
    public RecipeService(RecipeRepository recipeRepository, BeetStyleRepository beetStyleRepository) {
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
    }

    public Recipe setBasicParams(Long recipeId, BasicParamsPojo basicParamsPojo) {
        Recipe recipe = recipeRepository.getById(recipeId);
        recipe.setName(basicParamsPojo.getName());
        recipe.setBeerStyle(beetStyleRepository.getById(basicParamsPojo.getBeerStyleId()));
        recipe.setExpectedAmountOfBeer(basicParamsPojo.getExpectedAmountOfBeer());
        recipe.setTimeOfBoiling(basicParamsPojo.getTimeOfBoiling());
        recipe.setVaporisationSpeed(basicParamsPojo.getVaporisationSpeed());
        recipe.setBoilingLoss(basicParamsPojo.getBoilingLoss());
        recipe.setFermentationLoss(basicParamsPojo.getFermentationLoss());
        return recipe;
    }
}
