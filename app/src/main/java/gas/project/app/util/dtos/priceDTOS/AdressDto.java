package gas.project.app.util.dtos.priceDTOS;

import jakarta.validation.constraints.NotEmpty;

public record AdressDto(  
    @NotEmpty Long number, 
    @NotEmpty Integer cep, 
    @NotEmpty String city, 
    @NotEmpty String country, 
    @NotEmpty String state
){}
