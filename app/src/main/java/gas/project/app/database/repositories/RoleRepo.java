package gas.project.app.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.Role;


public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
