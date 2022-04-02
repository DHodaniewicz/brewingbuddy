package pl.brewingbuddy.converters;

import org.springframework.stereotype.Component;
import pl.brewingbuddy.entities.RecipeMalt;
import pl.brewingbuddy.pojo.RecipeMaltPojo;

@Component
public class RecipeMaltConverter {
    public RecipeMaltPojo toPojo(RecipeMalt recipeMalt) {
        RecipeMaltPojo recipeMaltPojo = new RecipeMaltPojo();
        recipeMaltPojo.setId(recipeMalt.getId());
        recipeMaltPojo.setRecipeId(recipeMalt.getRecipe().getId());
        recipeMaltPojo.setMaltId(recipeMalt.getMalt().getId());
        recipeMaltPojo.setAmount(recipeMalt.getAmount());
        return recipeMaltPojo;
    }
}
