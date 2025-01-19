package gas.project.app.configs;

import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.User;


import gas.project.app.database.repositories.RoleRepo;
import gas.project.app.database.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
class UserAdmindConfig implements CommandLineRunner {
    
    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder;

  

    private static final Logger log = LoggerFactory.getLogger(UserAdmindConfig.class);


    @Override
    @Transactional
    public void run(String... args){

        Optional<Role> roleAdmin = roleRepo.findByName(Role.Values.ADMIN.name());

        Optional<User> user = userRepo.findByusername("admin");

        if(roleAdmin.isEmpty()) throw new RuntimeException("Database table tb_role is null");

        user.ifPresentOrElse(
        existingUser -> {
            log.info("User admin already exists");
        },
        () -> {
            userRepo.save(new User().builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles(Set.of(roleAdmin.get()))  
                .build());
        }
        );
      
        

    }

}