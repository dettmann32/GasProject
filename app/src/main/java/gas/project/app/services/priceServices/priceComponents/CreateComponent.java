package gas.project.app.services.priceServices.priceComponents;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import gas.project.app.database.entitys.Adress;
import gas.project.app.database.entitys.Prices;
import gas.project.app.database.entitys.AdrresForeng.Cep;
import gas.project.app.database.entitys.AdrresForeng.City;
import gas.project.app.database.entitys.AdrresForeng.Country;
import gas.project.app.database.entitys.AdrresForeng.State;
import gas.project.app.database.repositories.AdressRepo;
import gas.project.app.database.repositories.PricesRepo;
import gas.project.app.database.repositories.RoleRepo;
import gas.project.app.database.repositories.UserRepo;
import gas.project.app.database.repositories.AdressForeng.CepRepo;
import gas.project.app.database.repositories.AdressForeng.CityRepo;
import gas.project.app.database.repositories.AdressForeng.CountryRepo;
import gas.project.app.database.repositories.AdressForeng.StateRepo;
import gas.project.app.services.priceServices.priceComponents.createComponentModules.FetchUser;
import gas.project.app.util.dtos.priceDTOS.CreatePriceDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateComponent {

    private final AdressRepo adressRepo;
    private final CepRepo cepRepo;
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    private final StateRepo stateRepo;
    private final PricesRepo pricesRepo;
    private final FetchUser fetchUser;

    @Transactional
    public void create(CreatePriceDto priceRegistration, JwtAuthenticationToken token){

        Adress adress = adressRepo.save(new Adress().builder()
        .cep(cepRepo.save(new Cep().builder().cep(priceRegistration.adress().cep()).build()))
        .city(cityRepo.save(new City().builder().City(priceRegistration.adress().city()).build()))
        .country(countryRepo.save(new Country().builder().country(priceRegistration.adress().country()).build()))
        .state(stateRepo.save(new State().builder().state(priceRegistration.adress().state()).build()))
        .number(priceRegistration.adress().number())
        .build());

        pricesRepo.save(new Prices().builder().adress(adress)
        .price(priceRegistration.price())
        .gasType(priceRegistration.gasType())
        .gasStation(priceRegistration.gasStation())
        .user(fetchUser.fetchUser(token))
        .build()
        );
    }
}
