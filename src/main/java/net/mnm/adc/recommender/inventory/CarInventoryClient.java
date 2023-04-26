package net.mnm.adc.recommender.inventory;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Client(value = "http://mnm-car-inventory:8082/cars") //works
@Client("http://${carinventory.service.name}:${carinventory.service.port}/cars")
@Recoverable(api = CarInventoryService.class)
public interface CarInventoryClient extends CarInventoryService {

    @Get("/stock/{model}")
    public Boolean isStockAvailable(@NotNull @NotBlank String model);

}
