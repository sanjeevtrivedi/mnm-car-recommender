package net.mnm.adc.recommender.inventory;

import io.micronaut.context.annotation.Value;
import io.micronaut.retry.annotation.Fallback;
import jakarta.inject.Singleton;

@Singleton
@Fallback
public class CarInventoryClientFallback implements CarInventoryService{

    @Value("${mnm.car.model1}")
    private String tharString;

    @Value("${mnm.car.model2}")
    private String xuv300String;

    @Override
    public Boolean isStockAvailable(String model) {
        if(model.equalsIgnoreCase(tharString)){
            return Boolean.TRUE;
        }

        if(model.equalsIgnoreCase(xuv300String)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
