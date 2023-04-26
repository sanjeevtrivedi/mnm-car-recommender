package net.mnm.adc.recommender;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import net.mnm.adc.recommender.inventory.CarInventoryService;
import net.mnm.adc.recommender.listing.CarListingService;

import java.util.ArrayList;
import java.util.List;

@Controller("/cars")
@ExecuteOn(TaskExecutors.IO)
public class CarController {

    private CarListingService carListingService;
    private CarInventoryService carInventoryService;

    public CarController(CarListingService carListingService, CarInventoryService carInventoryService) {
        this.carListingService = carListingService;
        this.carInventoryService = carInventoryService;
    }

    @Get("/recommend")
    public List<Car> getRecommendations(){
        List<Car> recommendedCars = new ArrayList<>();
        List<Car> listings = carListingService.getCars();
        for(Car car : listings){
            System.out.print(car.getModel() + ":" );
            Boolean stockAvailable = carInventoryService.isStockAvailable(car.getModel());
            System.out.println(stockAvailable);
            if(stockAvailable){
                recommendedCars.add(car);
            }
        }
        return recommendedCars;
    }

}
