package gas.project.app.database.repositories.AdressForeng;

import org.springframework.data.jpa.repository.JpaRepository;

import gas.project.app.database.entitys.AdrresForeng.City;

public interface CityRepo extends JpaRepository<City, Long>{
    
}
