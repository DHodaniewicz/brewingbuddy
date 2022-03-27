package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
