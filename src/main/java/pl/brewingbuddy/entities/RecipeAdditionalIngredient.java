package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "recipe_additional_ingredient")
@Getter
@Setter
@NoArgsConstructor
public class RecipeAdditionalIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDITIONALINGREDIENT_ID")
    private AdditionalIngredient additionalIngredient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;
    private Integer amount;
}
