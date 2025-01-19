package gas.project.app.databasTests;

import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.User;
import gas.project.app.database.repositories.RoleRepo;
import gas.project.app.database.repositories.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase
public class UserAndRole {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Test
    @DisplayName("Create user")
    void shouldCreatAuserAndReturnIt() {
        roleRepo.save(new Role().builder()
        .name(Role.Values.BASIC.name()
        ).roleId(2L).build());

        String username = "user";
        String password = "123456";

        Optional<Role> basic = roleRepo.findByName(Role.Values.BASIC.name());
        Optional<User> userDB = userRepo.findByusername(username);

        if (userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User newUser = new User().builder()
        .username(username)
        .password(password)
        .roles(Set.of(basic.get())).build();

        userRepo.save(newUser);

        Optional<User> database_user = userRepo.findByusername(username);

        Assertions.assertThat(database_user.get().getUsername()).isEqualTo(username);
        Assertions.assertThat(database_user.get().getRoles().toArray()[0]).isEqualTo(basic);


    }
}
