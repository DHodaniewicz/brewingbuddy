package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.brewingbuddy.entities.RecipeHop;

@Getter
@Setter
@NoArgsConstructor
public class RecipeHopPojo {
    private Long id;
    private Long hopId;
    private Long recipeId;
    private Integer timeOfBoiling;
    private Double amount;
    private String hopName;

}
