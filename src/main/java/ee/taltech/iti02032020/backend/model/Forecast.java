package ee.taltech.iti02032020.backend.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Locale;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue
    private Long id;
    private String countryName;
    private String city;
    private String weather;
    private String temperature;
    private String lon;
    private String lat;
    private String wind;
//    @OneToMany
//    private List<CoronaVirus> coronaVirus;


    public Forecast(String countryName, String city, String weather, String temperature, String lon, String lat, String wind) {
        this.countryName = countryName;
        this.city = city;
        this.weather = weather;
        this.temperature = temperature;
        this.lon = lon;
        this.lat = lat;
        this.wind = wind;

    }

    public static Forecast getForecastFromJson(String stringJson) {
        JsonObject json = new Gson().fromJson(stringJson, JsonObject.class);

        String country = json.get("sys").getAsJsonObject().get("country").toString().replace("\"", "");
        Locale loc = new Locale("en", country);
        String fullCountry = loc.getDisplayCountry();
        String city = json.get("name").toString().replace("\"", "");
        String weather =  json.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").toString();
        String temperature = json.get("main").getAsJsonObject().get("temp").toString();
        String lon =  json.get("coord").getAsJsonObject().get("lon").toString();
        String lat = json.get("coord").getAsJsonObject().get("lat").toString();
        String wind = json.get("wind").getAsJsonObject().get("speed").toString();
        return new Forecast(fullCountry, city, weather, temperature, lon, lat, wind);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }

}
