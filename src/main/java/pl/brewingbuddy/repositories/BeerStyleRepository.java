package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.BeerStyle;
@Repository
public interface BeerStyleRepository extends JpaRepository<BeerStyle,Long> {
}
