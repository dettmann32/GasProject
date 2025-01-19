package gas.project.app.util.dtos.priceDTOS;

import gas.project.app.database.entitys.Prices.GasType;
import jakarta.validation.constraints.NotEmpty;

public record CreatePriceDto(
    @NotEmpty AdressDto adress,
    @NotEmpty Double price,
    @NotEmpty String gasStation,
    @NotEmpty GasType gasType

) {

}

