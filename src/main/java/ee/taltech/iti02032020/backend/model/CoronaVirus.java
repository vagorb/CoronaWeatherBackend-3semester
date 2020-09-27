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


//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoronaVirus {

    @Id
    @GeneratedValue
    private Long id;
    private String countryName;
    private String totalCases;
    private String recoveredCases;
    private String totalDeaths;
    private String currentCases;
    @ManyToOne
    private Forecast forecast;

    public CoronaVirus(String countryName, String totalCases, String recoveredCases, String totalDeaths, String currentCases) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.recoveredCases = recoveredCases;
        this.totalDeaths = totalDeaths;
        this.currentCases = currentCases;
    }


}
