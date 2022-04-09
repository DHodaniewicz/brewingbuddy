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
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "HOP_ID")
    private Hop hop;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;
    private Integer timeOfBoiling;
    private Double amount;
}
