package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Malt;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeHop;
import pl.brewingbuddy.entities.RecipeMalt;

import java.util.Set;

@Repository
public interface RecipeMaltRepository extends JpaRepository<RecipeMalt, Long> {
    RecipeMalt findRecipeMaltByRecipeAndMalt(Recipe recipe, Malt malt);
    Set<RecipeMalt> findAllByRecipe(Recipe recipe);
}
