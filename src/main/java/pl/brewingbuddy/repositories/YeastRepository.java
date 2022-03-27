package pl.brewingbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brewingbuddy.entities.Yeast;
@Repository
public interface YeastRepository extends JpaRepository<Yeast, Long> {
}
