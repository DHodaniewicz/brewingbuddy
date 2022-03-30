package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Hop;
import pl.brewingbuddy.entities.RecipeHop;

@Repository
public interface RecipeHopRepository extends JpaRepository<RecipeHop,Long> {
}
