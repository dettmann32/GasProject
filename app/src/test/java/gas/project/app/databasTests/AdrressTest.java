package gas.project.app.databasTests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import gas.project.app.controllers.contollerTest;
import gas.project.app.database.entitys.Adress;
import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.AdrresForeng.Cep;
import gas.project.app.database.entitys.AdrresForeng.City;
import gas.project.app.database.entitys.AdrresForeng.Country;
import gas.project.app.database.entitys.AdrresForeng.State;
import gas.project.app.database.repositories.AdressRepo;
import gas.project.app.database.repositories.RoleRepo;
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

    private static final Logger logger = LoggerFactory.getLogger(contollerTest.class);


    @BeforeEach
    void setup(){
        roleRepo.save(new Role().builder().name("ADMIN").roleId(1L).build());
        roleRepo.save(new Role().builder().name("BASIC").roleId(2L).build());
    }

    @Test
    @DisplayName("Return valid data")
    void return_valid_data() {

        Adress adress = adressRepo.save(new Adress().builder()
        .cep(cepRepo.save(new Cep().builder().cep(29645000).build()))
        .city(cityRepo.save(new City().builder().city("santa maria de jetiba").build()))
        .country(countryRepo.save(new Country().builder().country("Brasil").build()))
        .state(stateRepo.save(new State().builder().state("Es").build()))
        .number(269L)
        .build());

        logger.info(adress.toString());

        
        Optional<Adress> requestAdress = adressRepo.findById(adress.getId());
        Optional<City> requestCity = cityRepo.findById(requestAdress.get().getCity().getId());

        Assertions.assertThat(requestAdress.get().getCity()).isEqualTo(requestCity.get().getCity());


    }
}
