package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Hop;
import pl.brewingbuddy.entities.Recipe;
import pl.brewingbuddy.entities.RecipeHop;

import java.util.Set;

@Repository
public interface RecipeHopRepository extends JpaRepository<RecipeHop,Long> {
    Set<RecipeHop> findAllByRecipe(Recipe recipe);
}
