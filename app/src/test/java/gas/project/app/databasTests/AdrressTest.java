package gas.project.app.databasTests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import gas.project.app.controllers.contollerTest;
import gas.project.app.database.entitys.Adress;
import gas.project.app.database.entitys.Prices;
import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.User;
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

@DataJpaTest
public class AdrressTest {

    @Autowired
    private AdressRepo adressRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private CepRepo cepRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private PricesRepo pricesRepo;

    @Autowired
    private UserRepo userRepo;



    private static final Logger logger = LoggerFactory.getLogger(contollerTest.class);


  

    @Test
    @DisplayName("Return valid adrress")
    void return_valid_data() {

        Adress adress = adressRepo.save(new Adress().builder()
        .cep(cepRepo.save(new Cep().builder().cep(29645000).build()))
        .city(cityRepo.save(new City().builder().City("santa maria de jetiba").build()))
        .country(countryRepo.save(new Country().builder().country("Brasil").build()))
        .state(stateRepo.save(new State().builder().state("Es").build()))
        .number(269L)
        .build());

        

        Optional<Adress> requestAdress = adressRepo.findById(adress.getId());
        Optional<City> requestCity = cityRepo.findById(requestAdress.get().getCity().getId());

        Assertions.assertThat(requestAdress.get().getCity().getCity())
        .isEqualTo(requestCity.get().getCity());


    }


    @Test
    @DisplayName("should return a valid price registry")
    void when_return_valid_priceRegistry(){

        Adress adress = adressRepo.save(new Adress().builder()
        .cep(cepRepo.save(new Cep().builder().cep(29645000).build()))
        .city(cityRepo.save(new City().builder().City("santa maria de jetiba").build()))
        .country(countryRepo.save(new Country().builder().country("Brasil").build()))
        .state(stateRepo.save(new State().builder().state("Es").build()))
        .number(269L)
        .build());

        Prices price = pricesRepo.save(new Prices().builder()
        .price(35)
        .user(userRepo.save(new User().builder().username("lucas").password("123456").roles(Set.of(new Role().builder().roleId(1L).name("admin").build())).build()))
        .gasType(Prices.GasType.GASOLINE)
        .build());

        Assertions.assertThat(price.getPrice()).isEqualTo(35);

    }
}
