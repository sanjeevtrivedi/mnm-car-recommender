package net.mnm.adc.recommender;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Introspected
public class Car {

    @NotNull
    @NotBlank
    private final String model;

    @NotNull
    @NotBlank
    private final String description;

    public Car(@NotNull @NotBlank String model, @NotNull @NotBlank String description) {
        this.model = model;
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car cars = (Car) o;

        if (!Objects.equals(model, cars.model)) return false;
        return Objects.equals(description, cars.description);
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
