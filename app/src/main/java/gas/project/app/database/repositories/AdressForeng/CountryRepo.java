package gas.project.app.database.repositories.AdressForeng;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gas.project.app.database.entitys.AdrresForeng.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long>{
    
}
