package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // General
    @ManyToOne
    private User user;
    private String name;
    @ManyToOne
    private BeerStyle beerStyle;
    private Boolean isPublic;
    // Gob
    private Double expectedAmountOfBeer; // [L]
    private Integer timeOfBoiling; // [min]
    private Double vaporisationSpeed; // [%/h]
    private Double boilingLoss; // [%]
    private Double fermentationLoss; // [%]
    // Gob - calculated
    private Double amountOfBoiledWort; // [L]
    private Double blgBeforeBoiling; // [blg]
    private Double amountOfWortAfterBoiling; // [L]
    //Fermenting ingredients
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<RecipeMalt> recipeMalt;
    // Mesh ingredients -calculated values
    private Double abv;
    private Double srm;
    private Double blg;
    //Mesh process
    private Double meshProcesTime; // [min]
    private Double meshProcessTemperature;  // [C deg]
    private Double waterMaltRatio; //  [L/kg]
    private Double meshPerformance; // [%]
    //Mesh process - calculated values
    private Double waterVolumeForMesh; // [L]
    private Double overallMeshVolume; // [kg]
    private Double waterVolumeForSparging; //[L]
    //Boiling
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<RecipeHop> recipeHop;
    //Boiling calculated
    private Double ibu;
    //Fermentation
    @ManyToOne
    private Yeast yeast;
    //Additional ingredients
    @OneToMany(mappedBy = "recipe")
    private Set<RecipeAdditionalIngredient> recipeAdditionalIngredient;
    //Notes
    private String notes;
    private Boolean isFinished;
}
