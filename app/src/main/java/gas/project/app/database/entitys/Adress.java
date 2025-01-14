package gas.project.app.database.entitys;

import gas.project.app.database.entitys.AdrresForeng.Cep;
import gas.project.app.database.entitys.AdrresForeng.City;
import gas.project.app.database.entitys.AdrresForeng.Country;
import gas.project.app.database.entitys.AdrresForeng.State;
import gas.project.app.database.entitys.AdrresForeng.Street;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "adress_id")
    private Long id;

    @Column(name = "number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "cep_id")
    private Cep cep;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;

}
