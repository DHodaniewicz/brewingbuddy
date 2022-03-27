package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "recipe_hop")
@Getter
@Setter
@NoArgsConstructor
public class RecipeHop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOP_ID")
    private Hop hop;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;
    private int timeOfBoiling;
    private int amount;
}