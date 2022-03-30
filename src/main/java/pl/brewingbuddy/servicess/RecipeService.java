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
import pl.brewingbuddy.repositories.YeastRepository;

@Getter
@Setter
@NoArgsConstructor
@Service
public class RecipeService {
    RecipeRepository recipeRepository;
    BeetStyleRepository beetStyleRepository;
    YeastRepository yeastRepository;

    public RecipeService(RecipeRepository recipeRepository, BeetStyleRepository beetStyleRepository, YeastRepository yeastRepository) {
        this.recipeRepository = recipeRepository;
        this.beetStyleRepository = beetStyleRepository;
        this.yeastRepository = yeastRepository;
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
        recipe.setMeshProcesTime(basicParamsPojo.getMeshProcesTime());
        recipe.setMeshProcessTemperature(basicParamsPojo.getMeshProcessTemperature());
        recipe.setWaterMaltRatio(basicParamsPojo.getWaterMaltRatio());
        recipe.setMeshPerformance(basicParamsPojo.getMeshPerformance());
        recipe.setYeast(yeastRepository.getById(basicParamsPojo.getBeerStyleId()));
        return recipe;
    }
}
