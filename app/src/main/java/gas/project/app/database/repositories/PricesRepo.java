package gas.project.app.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.Prices;

@Repository
public interface PricesRepo extends JpaRepository<Prices, Long>{
    
}
