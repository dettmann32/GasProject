package gas.project.app.database.entitys;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "price_id")
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    @CreationTimestamp
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "gas_station")
    private String gasStation;

    @Column(name = "gas_type")
    private GasType gasType;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    public enum GasType {
        DIESEL,
        GASOLINE,
        ETHANOL;
    }
}
