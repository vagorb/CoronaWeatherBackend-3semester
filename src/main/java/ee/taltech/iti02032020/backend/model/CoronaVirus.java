package ee.taltech.iti02032020.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoronaVirus {

    @Id
    @GeneratedValue
    private Long id;
    private String cityName;
    @ManyToOne
    private Forecast forecast;

    public CoronaVirus(String cityName) {
        this.cityName = cityName;
    }

    public CoronaVirus(String cityName, Long id) {
        this.id = id;
        this.cityName = cityName;

    }
}
