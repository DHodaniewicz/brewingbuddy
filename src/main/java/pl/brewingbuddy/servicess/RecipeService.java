package pl.brewingbuddy.servicess;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.pojo.BasicParamsPojo;
import pl.brewingbuddy.pojo.HopUtilization;
import pl.brewingbuddy.repositories.BeetStyleRepository;
import pl.brewingbuddy.repositories.RecipeRepository;
import pl.brewingbuddy.repositories.YeastRepository;

import java.util.Set;

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

    public Recipe calculateAmountOfBoiledWort(Recipe recipe) {
        if (recipe.getExpectedAmountOfBeer() == null || recipe.getFermentationLoss() == null) {
            return recipe;
        }
        Double amountOfBoiledWort = recipe.getExpectedAmountOfBeer() + ((recipe.getExpectedAmountOfBeer() * recipe.getFermentationLoss()) / 100);
        recipe.setAmountOfBoiledWort(amountOfBoiledWort);
        return recipe;
    }

    public Recipe calculateAmountOfWortAfterBoiling(Recipe recipe){
        if (recipe.getExpectedAmountOfBeer() == null || recipe.getTimeOfBoiling() == null || recipe.getVaporisationSpeed() == null || recipe.getBoilingLoss() == null) {
            return recipe;
        }
        Double amountOfWortAfterBoiling = recipe.getExpectedAmountOfBeer() * ((recipe.getTimeOfBoiling() / 60) * (recipe.getVaporisationSpeed() / 100));
        amountOfWortAfterBoiling += ((amountOfWortAfterBoiling * recipe.getBoilingLoss()) / 100);
        recipe.setAmountOfWortAfterBoiling(amountOfWortAfterBoiling);
        return recipe;
    }

    public Recipe calculateBlgBeforeBoiling(Recipe recipe){
        if(recipe.getAmountOfBoiledWort() == null || recipe.getAmountOfWortAfterBoiling() == null || recipe.getBlg() == null) {
            return recipe;
        }
        Double blgBeforeBoiling = (recipe.getAmountOfBoiledWort() / recipe.getAmountOfWortAfterBoiling()) * recipe.getBlg();
        recipe.setBlgBeforeBoiling(blgBeforeBoiling);
        return recipe;
    }

    public Recipe calculateOverallMeshVolume(Recipe recipe){
        Double overallMeshVolume = 0.0;
         Set <RecipeMalt> recipeMalts = recipe.getRecipeMalt();

        if (recipeMalts.isEmpty()) {
            return recipe;
        }
        for (RecipeMalt recipeMalt : recipeMalts) {
            overallMeshVolume += recipeMalt.getAmount();
        }
        recipe.setOverallMeshVolume(overallMeshVolume);
        return recipe;
    }

    public Recipe calculateWaterVolumeForMesh(Recipe recipe) {
        if (recipe.getOverallMeshVolume() == null || recipe.getWaterMaltRatio() == null) {
            return recipe;
        }
        Double waterVolumeForMesh = recipe.getOverallMeshVolume() * recipe.getWaterMaltRatio();
        recipe.setWaterVolumeForMesh(waterVolumeForMesh);
        return recipe;
    }

    public Recipe calculateWaterVolumeForSparging(Recipe recipe) {
        if (recipe.getAmountOfBoiledWort() == null || recipe.getWaterVolumeForMesh() == null ) {
            return recipe;
        }
        double waterVolumeForSparging;
        waterVolumeForSparging = recipe.getAmountOfBoiledWort() - recipe.getWaterVolumeForMesh();
        recipe.setWaterVolumeForSparging(waterVolumeForSparging);
        return recipe;
    }

    public Recipe calculateBlg(Recipe recipe) {

        if (recipe.getRecipeMalt().isEmpty() || recipe.getAmountOfWortAfterBoiling() == null) {
            return recipe;
        }
        Double extract = 0.0;
        Double blg = 0.0;

        for(RecipeMalt recipeMalt : recipe.getRecipeMalt()) {
            extract += recipeMalt.getAmount() * recipeMalt.getMalt().getExtractionRate();
        }

        blg = (extract / recipe.getAmountOfWortAfterBoiling()) * recipe.getMeshPerformance();

        recipe.setBlg(blg);
        return recipe;
    }

    public Recipe calculateAbv (Recipe recipe) {
        if (recipe.getBlg() == null) {
            return recipe;
        }
        final Double parameter = 0.544;
        final Double finalBlg = 2.5;

        Double abv = (recipe.getBlg() - 2.5) * parameter;
        recipe.setAbv(abv);
        return recipe;
    }

    public Recipe calculateSrm(Recipe recipe) {

        if (recipe.getRecipeMalt().isEmpty() || recipe.getAmountOfWortAfterBoiling() == null) {
            return recipe;
        }
        final Double parameter = 1.4922;
        Double sumSrm = 0.0;

        for(RecipeMalt recipeMalt : recipe.getRecipeMalt()) {
            sumSrm = (recipeMalt.getAmount() * 2.2) * (recipeMalt.getMalt().getEbc() /1.97);
        }

        Double srm = 1.97 * (1.4922 *  Math.pow((sumSrm / ((recipe.getAmountOfBoiledWort()/1000) / 3.7854)), 0.6859));

        recipe.setSrm(srm);
        return recipe;
    }

    public Recipe calculateIbu(Recipe recipe) {
        if (recipe.getRecipeHop().isEmpty() || recipe.getAmountOfBoiledWort() == null) {
            return recipe;
        }

        Double ibu = 0.0;

        for(RecipeHop recipeHop : recipe.getRecipeHop()) {
            ibu += recipeHop.getAmount() * recipeHop.getHop().getAlfaAcid() *
                    HopUtilization.getHopPercentageUtilization(recipeHop.getTimeOfBoiling()) /
                    (recipe.getAmountOfBoiledWort() / 1000 * 10);
        }
        recipe.setIbu(ibu);
        return recipe;
    }

    public Recipe calculateRecipe(Recipe recipe) {
        recipe = calculateAmountOfBoiledWort(recipe);
        recipe = calculateAmountOfWortAfterBoiling(recipe);
        recipe = calculateBlg(recipe);
        recipe = calculateOverallMeshVolume(recipe);
        recipe = calculateWaterVolumeForMesh(recipe);
        recipe = calculateWaterVolumeForSparging(recipe);
        recipe = calculateBlgBeforeBoiling(recipe);
        recipe = calculateAbv(recipe);
        recipe = calculateSrm(recipe);
        recipe = calculateIbu(recipe);
        return recipe;
    }

}


