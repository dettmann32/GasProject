package gas.project.app.database.entitys.AdrresForeng;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_cep")
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cep_id")
    private Long id;

    @Column(name = "cep", nullable = false, unique = true, length = 8)
    private Integer cep;

    
}
