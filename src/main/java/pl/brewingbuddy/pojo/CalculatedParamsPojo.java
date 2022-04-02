package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalculatedParamsPojo {

    private Double amountOfBoiledWort;
    private Double blgBeforeBoiling;
    private Double amountOfWortAfterBoiling;
    private Double overallMeshVolume;
    private Double waterVolumeForMesh;
    private Double waterVolumeForSparging;
    private Double abv;
    private Double srm;
    private Double blg;
    private Double ibu;
}
