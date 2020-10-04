package ee.taltech.iti02032020.backend.model;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



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

    public CoronaVirus(String countryName, String totalCases, String recoveredCases, String totalDeaths, String currentCases) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.recoveredCases = recoveredCases;
        this.totalDeaths = totalDeaths;
        this.currentCases = currentCases;
    }

    public static CoronaVirus getCoronaVirusFromJson(String stringJson, String country) {
        JsonObject json = new Gson().fromJson(stringJson, JsonObject.class);
        String totalCases = json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("total_cases").toString();
        String totalRecovered =  json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("recovered").toString();
        String totalDeaths = json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("deaths").toString();
        String currentCases = json.get("data").getAsJsonObject().get("change").getAsJsonObject().get("total_cases").toString();
        return new CoronaVirus(country, totalCases, totalRecovered, totalDeaths, currentCases);
    }

}
