package pl.brewingbuddy.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "hop")
    private Set<RecipeHop> recipeHops;
    private String name;
    private Double alfaAcid;
    private String manufacturer;

}
