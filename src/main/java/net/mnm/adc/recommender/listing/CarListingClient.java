package net.mnm.adc.recommender.listing;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;
import net.mnm.adc.recommender.Car;

import java.util.List;

//@Client("http://mnm-car-listing:8081/cars") //works
//@Client("${carlisting.service.url:`http://localhost:8081/cars`}") //works
@Client("http://${carlisting.service.name}:${carlisting.service.port}/cars") //works
@Recoverable(api = CarListingService.class)
public interface CarListingClient extends CarListingService {

    @Get("/listing")
    public List<Car> getCars();

}
