package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BeerStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String beerStyle;
    @OneToMany(mappedBy = "beerStyle")
    private Set<Recipe> recipes;
}
