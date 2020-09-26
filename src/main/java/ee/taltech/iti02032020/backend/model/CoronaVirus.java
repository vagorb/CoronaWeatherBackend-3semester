package ee.taltech.iti02032020.backend.model;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter

public class CoronaVirus {

    @Id
    @GeneratedValue
    private Long id;
    private String cityName;


    public CoronaVirus() {
    }

    public CoronaVirus(String cityName) {
        this.cityName = cityName;
    }

    public CoronaVirus(String cityName, Long id) {
        this.id = id;
        this.cityName = cityName;

    }


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
