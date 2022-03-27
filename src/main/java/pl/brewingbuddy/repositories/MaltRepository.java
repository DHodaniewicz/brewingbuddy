package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Malt;
@Repository
public interface MaltRepository extends JpaRepository<Malt, Long> {
}
