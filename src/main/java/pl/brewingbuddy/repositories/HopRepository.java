package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Hop;
@Repository
public interface HopRepository extends JpaRepository<Hop, Long> {
}
