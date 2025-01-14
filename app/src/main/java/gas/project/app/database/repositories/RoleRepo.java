package gas.project.app.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
    
}
