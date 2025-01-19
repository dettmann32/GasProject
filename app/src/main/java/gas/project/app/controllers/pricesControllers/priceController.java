package gas.project.app.controllers.pricesControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import gas.project.app.services.priceServices.PriceService;
import gas.project.app.util.dtos.priceDTOS.CreatePriceDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController("/prices")
public class priceController {
    private final PriceService priceService;
    
    @PostMapping("/createRegistryPrice")
    public ResponseEntity<Void> creat_a_PriceRegistry(@RequestBody @Valid CreatePriceDto data, JwtAuthenticationToken token){
        priceService.create_price_registration(data, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getPrices")
    public void getPrice(){

    }
}
