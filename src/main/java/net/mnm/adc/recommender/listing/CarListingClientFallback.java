package net.mnm.adc.recommender.listing;

import io.micronaut.context.annotation.Value;
import io.micronaut.retry.annotation.Fallback;
import jakarta.inject.Singleton;
import net.mnm.adc.recommender.Car;

import java.util.Arrays;
import java.util.List;

@Singleton
@Fallback
public class CarListingClientFallback implements CarListingService{

    @Value("${mnm.car.model1}")
    private String tharString;

    @Value("${mnm.car.model2}")
    private String xuv300String;
    @Override
    public List<Car> getCars() {
        return Arrays.asList(
                new Car(tharString, "The one and only 4x4 Offroader."),
                new Car(xuv300String, "Ssyangyong Tivoli under 4 meter with 5 star rating.")
        );
    }
}
