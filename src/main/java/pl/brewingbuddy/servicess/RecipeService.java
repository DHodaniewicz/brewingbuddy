package pl.brewingbuddy.servicess;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.pojo.BasicParamsPojo;
import pl.brewingbuddy.pojo.CalculatedParamsPojo;
import pl.brewingbuddy.pojo.HopUtilization;
import pl.brewingbuddy.repositories.BeerStyleRepository;
import pl.brewingbuddy.repositories.RecipeRepository;
import pl.brewingbuddy.repositories.YeastRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Service
public class RecipeService {
    RecipeRepository recipeRepository;
    BeerStyleRepository beerStyleRepository;
    YeastRepository yeastRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, BeerStyleRepository beerStyleRepository, YeastRepository yeastRepository) {
        this.recipeRepository = recipeRepository;
        this.beerStyleRepository = beerStyleRepository;
        this.yeastRepository = yeastRepository;
    }

    public Recipe setBasicParams(Recipe recipe, BasicParamsPojo basicParamsPojo) {
        recipe.setName(basicParamsPojo.getName());
        if (basicParamsPojo.getBeerStyleId() != null) {
            recipe.setBeerStyle(beerStyleRepository.getById(basicParamsPojo.getBeerStyleId()));
        }
        recipe.setExpectedAmountOfBeer(basicParamsPojo.getExpectedAmountOfBeer());
        recipe.setTimeOfBoiling(basicParamsPojo.getTimeOfBoiling());
        recipe.setVaporisationSpeed(basicParamsPojo.getVaporisationSpeed());
        recipe.setBoilingLoss(basicParamsPojo.getBoilingLoss());
        recipe.setFermentationLoss(basicParamsPojo.getFermentationLoss());
        recipe.setMeshProcesTime(basicParamsPojo.getMeshProcesTime());
        recipe.setMeshProcessTemperature(basicParamsPojo.getMeshProcessTemperature());
        recipe.setWaterMaltRatio(basicParamsPojo.getWaterMaltRatio());
        recipe.setMeshPerformance(basicParamsPojo.getMeshPerformance());
        if (basicParamsPojo.getYeastId() != null) {
            recipe.setYeast(yeastRepository.getById(basicParamsPojo.getYeastId()));
        }
        recipe.setIsPublic(basicParamsPojo.getIsPublic());
        return recipe;
    }

    public CalculatedParamsPojo getCalculatedParams(Recipe recipe) {

        recipe = roundRecipeDetailsValues(recipe);

        CalculatedParamsPojo calculatedParamsPojo = new CalculatedParamsPojo();
        calculatedParamsPojo.setAmountOfBoiledWort(recipe.getAmountOfBoiledWort());
        //BigDecimal bdAmountOfBoiledWort = new BigDecimal(recipe.getAmountOfBoiledWort()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setAmountOfBoiledWort(bdAmountOfBoiledWort.doubleValue());

        calculatedParamsPojo.setBlgBeforeBoiling(recipe.getBlgBeforeBoiling());
        //BigDecimal bdBlgBeforeBoiling = new BigDecimal(recipe.getBlgBeforeBoiling()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setBlgBeforeBoiling(bdBlgBeforeBoiling.doubleValue());

        calculatedParamsPojo.setAmountOfWortAfterBoiling(recipe.getAmountOfWortAfterBoiling());
        //BigDecimal bdAmountOfWortAfterBoiling = new BigDecimal(recipe.getAmountOfWortAfterBoiling()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setAmountOfWortAfterBoiling(bdAmountOfWortAfterBoiling.doubleValue());

        calculatedParamsPojo.setOverallMeshVolume(recipe.getOverallMeshVolume());
        //BigDecimal bdOverallMeshVolume = new BigDecimal(recipe.getOverallMeshVolume()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setOverallMeshVolume(bdOverallMeshVolume.doubleValue());

        calculatedParamsPojo.setWaterVolumeForMesh(recipe.getWaterVolumeForMesh());
        //BigDecimal bdWaterVolumeForMesh = new BigDecimal(recipe.getWaterVolumeForMesh()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setWaterVolumeForMesh(bdWaterVolumeForMesh.doubleValue());

        calculatedParamsPojo.setWaterVolumeForSparging(recipe.getWaterVolumeForSparging());
        //BigDecimal bdWaterVolumeForSparging = new BigDecimal(recipe.getWaterVolumeForSparging()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setWaterVolumeForSparging(bdWaterVolumeForSparging.doubleValue());

        calculatedParamsPojo.setAbv(recipe.getAbv());
        //BigDecimal bdAbv = new BigDecimal(recipe.getAbv()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setAbv(bdAbv.doubleValue());

        calculatedParamsPojo.setSrm(recipe.getSrm());
        //BigDecimal bdSrm = new BigDecimal(recipe.getSrm()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setSrm(bdSrm.doubleValue());

        calculatedParamsPojo.setBlg(recipe.getBlg());
        //BigDecimal bdBlg = new BigDecimal(recipe.getBlg()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setBlg(bdBlg.doubleValue());

        calculatedParamsPojo.setIbu(recipe.getIbu());
        //BigDecimal bdIbu = new BigDecimal(recipe.getIbu()).setScale(2, RoundingMode.HALF_UP);
        //calculatedParamsPojo.setIbu(bdIbu.doubleValue());

        return calculatedParamsPojo;
    }

    public Recipe setBasicParamsOfCreatedRecipe(Recipe recipe) {
        recipe.setTimeOfBoiling(60);
        recipe.setVaporisationSpeed(5.0);
        recipe.setBoilingLoss(5.0);
        recipe.setFermentationLoss(5.0);
        recipe.setMeshProcesTime(60.0);
        recipe.setMeshProcessTemperature(67.0);
        recipe.setWaterMaltRatio(3.5);
        recipe.setMeshPerformance(75.0);
        return recipe;
    }

    public List<Recipe> roundRecipeValues (List <Recipe> recipeList) {
        if (recipeList.isEmpty()) {
            return recipeList;
        }
        recipeList.forEach(recipe -> {
            if(recipe.getIbu() != null & recipe.getBlg() != null) {
                BigDecimal bdIbu = new BigDecimal(recipe.getIbu()).setScale(2, RoundingMode.HALF_UP);
                recipe.setIbu(bdIbu.doubleValue());
                BigDecimal bdBlg = new BigDecimal(recipe.getBlg()).setScale(2, RoundingMode.HALF_UP);
                recipe.setBlg(bdBlg.doubleValue());
            }

        });
        return recipeList;
    }

    public Recipe roundRecipeDetailsValues (Recipe recipe) {

        if (recipe.getAmountOfBoiledWort() != null) {
            BigDecimal bdAmountOfBoiledWort = new BigDecimal(recipe.getAmountOfBoiledWort()).setScale(2, RoundingMode.HALF_UP);
            recipe.setAmountOfBoiledWort(bdAmountOfBoiledWort.doubleValue());
        }

        if(recipe.getBlgBeforeBoiling() != null) {
            BigDecimal bdBlgBeforeBoiling = new BigDecimal(recipe.getBlgBeforeBoiling()).setScale(2, RoundingMode.HALF_UP);
            recipe.setBlgBeforeBoiling(bdBlgBeforeBoiling.doubleValue());
        }

        if(recipe.getAmountOfWortAfterBoiling() !=  null) {
            BigDecimal bdAmountOfWortAfterBoiling = new BigDecimal(recipe.getAmountOfWortAfterBoiling()).setScale(2, RoundingMode.HALF_UP);
            recipe.setAmountOfWortAfterBoiling(bdAmountOfWortAfterBoiling.doubleValue());
        }

        if(recipe.getOverallMeshVolume() != null) {
            BigDecimal bdOverallMeshVolume = new BigDecimal(recipe.getOverallMeshVolume()).setScale(2, RoundingMode.HALF_UP);
            recipe.setOverallMeshVolume(bdOverallMeshVolume.doubleValue());
        }

        if(recipe.getWaterVolumeForMesh() != null) {
            BigDecimal bdWaterVolumeForMesh = new BigDecimal(recipe.getWaterVolumeForMesh()).setScale(2, RoundingMode.HALF_UP);
            recipe.setWaterVolumeForMesh(bdWaterVolumeForMesh.doubleValue());
        }

        if(recipe.getWaterVolumeForSparging() != null) {
            BigDecimal bdWaterVolumeForSparging = new BigDecimal(recipe.getWaterVolumeForSparging()).setScale(2, RoundingMode.HALF_UP);
            recipe.setWaterVolumeForSparging(bdWaterVolumeForSparging.doubleValue());
        }

        if(recipe.getAbv() != null) {
            BigDecimal bdAbv = new BigDecimal(recipe.getAbv()).setScale(2, RoundingMode.HALF_UP);
            recipe.setAbv(bdAbv.doubleValue());
        }

        if(recipe.getSrm() != null) {
            BigDecimal bdSrm = new BigDecimal(recipe.getSrm()).setScale(2, RoundingMode.HALF_UP);
            recipe.setSrm(bdSrm.doubleValue());
        }

        if (recipe.getBlg() != null) {
            BigDecimal bdBlg = new BigDecimal(recipe.getBlg()).setScale(2, RoundingMode.HALF_UP);
            recipe.setBlg(bdBlg.doubleValue());
        }

        if (recipe.getIbu() != null) {
            BigDecimal bdIbu = new BigDecimal(recipe.getIbu()).setScale(2, RoundingMode.HALF_UP);
            recipe.setIbu(bdIbu.doubleValue());
        }
        return recipe;
    }



    public Recipe calculateAmountOfBoiledWort(Recipe recipe) {
        if (recipe.getExpectedAmountOfBeer() == null || recipe.getTimeOfBoiling() == null || recipe.getVaporisationSpeed() == null || recipe.getBoilingLoss() == null) {
            return recipe;
        }
        Double expectedAmountOfBeer = recipe.getExpectedAmountOfBeer();
        Integer timeOfBoiling = recipe.getTimeOfBoiling();
        Double VaporisationSpeed = recipe.getVaporisationSpeed();

        Double amountOfBoiledWort = expectedAmountOfBeer + (expectedAmountOfBeer * (timeOfBoiling / 60) * (VaporisationSpeed) / 100);

        amountOfBoiledWort = amountOfBoiledWort + ((amountOfBoiledWort * recipe.getBoilingLoss()) / 100);

        recipe.setAmountOfBoiledWort(amountOfBoiledWort);
        return recipe;
    }

    public Recipe calculateAmountOfWortAfterBoiling(Recipe recipe){
            if (recipe.getExpectedAmountOfBeer() == null || recipe.getFermentationLoss() == null) {
                return recipe;
            }
            Double amountOfWortAfterBoiling = recipe.getExpectedAmountOfBeer() + ((recipe.getExpectedAmountOfBeer() * recipe.getFermentationLoss()) / 100);
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

    public Recipe calculateOverallMeshVolume(Recipe recipe) {
        Double overallMeshVolume = 0.0;

        if (recipe.getWaterVolumeForMesh() == null || recipe.getWaterMaltRatio() == null) {
            return recipe;
        }

            overallMeshVolume = recipe.getWaterVolumeForMesh() + (recipe.getWaterVolumeForMesh() / recipe.getWaterMaltRatio());
            recipe.setOverallMeshVolume(overallMeshVolume);
            return recipe;
        }


    public Recipe calculateWaterVolumeForMesh(Recipe recipe) {
        Double overallMaltWeight = 0.0;
        Set <RecipeMalt> recipeMalts = recipe.getRecipeMalt();

        if (recipe.getWaterMaltRatio() == null || recipeMalts.isEmpty()) {
            return recipe;
        }

        for (RecipeMalt recipeMalt : recipeMalts) {
            overallMaltWeight += recipeMalt.getAmount();
        }

        Double waterVolumeForMesh = overallMaltWeight * recipe.getWaterMaltRatio();
        recipe.setWaterVolumeForMesh(waterVolumeForMesh);
        return recipe;
    }

    public Recipe calculateWaterVolumeForSparging(Recipe recipe) {
        if (recipe.getAmountOfBoiledWort() == null || recipe.getWaterVolumeForMesh() == null ) {
            return recipe;
        }
        double waterVolumeForSparging;
        waterVolumeForSparging = recipe.getAmountOfBoiledWort() - recipe.getWaterVolumeForMesh();
        if (waterVolumeForSparging < 0) {
            waterVolumeForSparging = 0;
        }
        recipe.setWaterVolumeForSparging(waterVolumeForSparging);
        return recipe;
    }

    public Recipe calculateBlg(Recipe recipe) {

        if (recipe.getRecipeMalt().isEmpty() || recipe.getAmountOfWortAfterBoiling() == null || recipe.getMeshPerformance() == null) {
            return recipe;
        }
        Double extract = 0.0;
        Double blg = 0.0;

        for(RecipeMalt recipeMalt : recipe.getRecipeMalt()) {
            extract += recipeMalt.getAmount() * recipeMalt.getMalt().getExtractionRate();
        }

        blg = (extract / recipe.getAmountOfWortAfterBoiling()) * recipe.getMeshPerformance()/100;

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

        if(abv < 0) {
            abv = 0.0;
        }

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

        Double srm = 1.97 * (1.4922 *  Math.pow((sumSrm / ((recipe.getAmountOfBoiledWort()/1000) / 3.7854)), 0.6859))/100;

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
                    (recipe.getAmountOfBoiledWort() * 10);
        }
        recipe.setIbu(ibu);
        return recipe;
    }

    public Recipe calculateRecipe(Recipe recipe) {
        recipe = calculateAmountOfBoiledWort(recipe);
        recipe = calculateAmountOfWortAfterBoiling(recipe);
        recipe = calculateBlg(recipe);
        recipe = calculateWaterVolumeForMesh(recipe);
        recipe = calculateOverallMeshVolume(recipe);
        recipe = calculateWaterVolumeForSparging(recipe);
        recipe = calculateBlgBeforeBoiling(recipe);
        recipe = calculateAbv(recipe);
        recipe = calculateSrm(recipe);
        recipe = calculateIbu(recipe);
        CalculatedParamsPojo calculatedParamsPojo = new CalculatedParamsPojo();
        calculatedParamsPojo = getCalculatedParams(recipe);
        return recipe;
    }

}


