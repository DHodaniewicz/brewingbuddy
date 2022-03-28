package pl.brewingbuddy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class TestPojo {
    Double expectedAmountOfBeer;
    Double timeOfBoiling;

    public TestPojo(Double expectedAmountOfBeer, Double timeOfBoiling) {
        this.expectedAmountOfBeer = expectedAmountOfBeer;
        this.timeOfBoiling = timeOfBoiling;
    }
}
