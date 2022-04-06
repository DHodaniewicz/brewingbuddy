package pl.brewingbuddy.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.pojo.RecipeMaltPojo;
import pl.brewingbuddy.repositories.MaltRepository;
import pl.brewingbuddy.repositories.RecipeMaltRepository;
import pl.brewingbuddy.repositories.RecipeRepository;

@Component
public class RecipeMaltConverter {
    RecipeMaltRepository recipeMaltRepository;
    RecipeRepository recipeRepository;
    MaltRepository maltRepository;

    @Autowired
    public RecipeMaltConverter(RecipeMaltRepository recipeMaltRepository, RecipeRepository recipeRepository, MaltRepository maltRepository) {
        this.recipeMaltRepository = recipeMaltRepository;
        this.recipeRepository = recipeRepository;
        this.maltRepository = maltRepository;
    }

    public RecipeMaltPojo toPojo(RecipeMalt recipeMalt) {
        RecipeMaltPojo recipeMaltPojo = new RecipeMaltPojo();
        recipeMaltPojo.setId(recipeMalt.getId());
        recipeMaltPojo.setRecipeId(recipeMalt.getRecipe().getId());
        recipeMaltPojo.setMaltId(recipeMalt.getMalt().getId());
        recipeMaltPojo.setAmount(recipeMalt.getAmount());
        recipeMaltPojo.setMaltName(recipeMalt.getMalt().getName());
        return recipeMaltPojo;
    }

    public RecipeMalt toEntity (RecipeMaltPojo recipeMaltPojo) {
        RecipeMalt recipeMalt = recipeMaltRepository.getById(recipeMaltPojo.getId());
        recipeMalt.setMalt(maltRepository.getById(recipeMaltPojo.getMaltId()));
        recipeMalt.setAmount(recipeMaltPojo.getAmount());
        return recipeMalt;
    }


}
