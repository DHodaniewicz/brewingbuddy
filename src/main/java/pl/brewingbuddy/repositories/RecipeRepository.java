package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.BeerStyle;
import pl.brewingbuddy.entities.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByBeerStyle(BeerStyle beerStyle);
    List<Recipe> findAllByUserId(Long userId);
}
