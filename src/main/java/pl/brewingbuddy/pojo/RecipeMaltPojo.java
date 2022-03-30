package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeMaltPojo {
    private Long id;
    private Long maltId;
    private Long recipeId;
    private Integer amount;
}
