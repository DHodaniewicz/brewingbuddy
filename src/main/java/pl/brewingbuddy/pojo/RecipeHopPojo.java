package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeHopPojo {
    private Long id;
    private Long hopId;
    private Long recipeId;
    private int timeOfBoiling;
    private int amount;
}
