package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.AdditionalIngredient;
@Repository
public interface AdditionalIngredientRepository extends JpaRepository<AdditionalIngredient, Long> {
}
