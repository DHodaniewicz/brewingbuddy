package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private boolean isPublic;
    // Gob
    private Double expectedAmountOfBeer;
    private Integer timeOfBoiling;
    private Double vaporisationSpeed;
    private Double boilingLoss;
    private Double fermentationLoss;
    // Gob - calculated
    private String amountOfBoiledWort;
    private Double blgBeforeBoiling;
    private Double amountOfWortAfterBoiling;
    //Fermenting ingredients
    @OneToMany(mappedBy = "recipe")
    private Set<RecipeMalt> recipeMalt;
    // Mesh ingredients -calculated values
    private Double abv;
    private Double srm;
    private Double blg;
    //Mesh process
    private Double meshProcesTime;
    private Double meshProcessTemperature;
    private Double waterMaltRatio;
    private Double meshPerformance;
    //Mesh process - calculated values
    private Double waterVolumeForMesh;
    private Double overallMeshVolume;
    //Boiling
    @OneToMany(mappedBy = "recipe")
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


}
