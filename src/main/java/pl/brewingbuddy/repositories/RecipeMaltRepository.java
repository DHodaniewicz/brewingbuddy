package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.RecipeMalt;
@Repository
public interface RecipeMaltRepository extends JpaRepository<RecipeMalt, Long> {
}
