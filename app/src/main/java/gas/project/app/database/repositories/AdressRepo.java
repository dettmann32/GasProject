package gas.project.app.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.Adress;

@Repository
public interface AdressRepo extends JpaRepository<Adress, Long> {
    
}
