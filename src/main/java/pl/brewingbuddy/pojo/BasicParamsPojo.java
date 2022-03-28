package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicParamsPojo {
    private String name;
    private Long beerStyleId;
    private Double expectedAmountOfBeer;
    private Integer timeOfBoiling;
    private Double vaporisationSpeed;
    private Double boilingLoss;
    private Double fermentationLoss;

    @Override
    public String toString() {
        return "BasicParamsPojo{" +
                "name='" + name + '\'' +
                ", beerStyleId=" + beerStyleId +
                ", expectedAmountOfBeer=" + expectedAmountOfBeer +
                ", timeOfBoiling=" + timeOfBoiling +
                ", vaporisationSpeed=" + vaporisationSpeed +
                ", boilingLoss=" + boilingLoss +
                ", fermentationLoss=" + fermentationLoss +
                '}';
    }
}
