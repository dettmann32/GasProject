package gas.project.app.integrationTests.controllers.loginControllers;

import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.boot.test.web.client.TestRestTemplate;

import gas.project.app.AppApplication;
import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.User;
import gas.project.app.database.repositories.RoleRepo;
import gas.project.app.database.repositories.UserRepo;



@SpringBootTest()
@AutoConfigureMockMvc
public class loginController {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private MockMvc mockMvc;
    
        
    @LocalServerPort
    private int port;
 
    @Test
    @DisplayName("Login endpoint")
    void should_return_JwtToken() throws Exception{
        

        roleRepo.save(new Role().builder()
        .name(Role.Values.BASIC.name()
        ).roleId(2L).build());

        
        String username = "user";
        String password = "123456";
        String json = "{\"username\":\""+ username +"\", \"password\":\"" + password +"\"}";

        
        Optional<User> userDB = userRepo.findByusername(username);

        if (userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Optional<Role> basic = roleRepo.findByName(Role.Values.BASIC.name());
        User newUser = new User().builder()
        .username(username)
        .password(password)
        .roles(Set.of(basic.get().builder().name(Role.Values.BASIC.name()).roleId(2L).build())).build();

        userRepo.save(newUser);
        
        mockMvc.perform(MockMvcRequestBuilders.post("auth/login")
        .contentType(MediaType.APPLICATION_JSON)  // Definindo o tipo do conteúdo como JSON
        .content(json)).andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
