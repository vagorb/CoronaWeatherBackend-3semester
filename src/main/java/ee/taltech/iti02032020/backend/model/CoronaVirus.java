package ee.taltech.iti02032020.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CoronaVirus {

    @Id
    @GeneratedValue
    private String cityName;// countryName
    private Long id;

    public CoronaVirus() {
    }

    // Teacher had 3 constructors



    public Long getId() {
        return id;
    }

    public CoronaVirus setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public CoronaVirus setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }
}
