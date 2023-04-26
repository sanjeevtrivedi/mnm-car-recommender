package net.mnm.adc.recommender.inventory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface CarInventoryService {

    public Boolean isStockAvailable(@NotBlank @NotNull String model);

}
