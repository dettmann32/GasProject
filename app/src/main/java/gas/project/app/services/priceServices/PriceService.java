package gas.project.app.services.priceServices;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import gas.project.app.services.priceServices.priceComponents.CreateComponent;
import gas.project.app.util.dtos.priceDTOS.CreatePriceDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceService {
    
    private final CreateComponent createComponent;

    public void create_price_registration(CreatePriceDto priceRegistration, JwtAuthenticationToken token){
        
        createComponent.create(priceRegistration, token);
    }
}
