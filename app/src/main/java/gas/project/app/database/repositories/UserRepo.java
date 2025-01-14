package gas.project.app.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    
}
