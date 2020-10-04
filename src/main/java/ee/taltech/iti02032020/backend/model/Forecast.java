package ee.taltech.iti02032020.backend.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import java.io.IOException;

import java.util.Locale;


@Entity
@Data
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
    private String pressure;
    private String humidity;
    @ManyToOne
    CoronaVirus coronaVirus;

    public Forecast(String countryName, String city, String weather, String temperature, String lon, String lat, String wind, String pressure, String humidity) {
        this.countryName = countryName;
        this.city = city;
        this.weather = weather;
        this.temperature = temperature;
        this.lon = lon;
        this.lat = lat;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public static Forecast getForecastFromJson(String stringJson) throws IOException {
        JsonObject json = new Gson().fromJson(stringJson, JsonObject.class);
        String country = json.get("sys").getAsJsonObject().get("country").toString().replace("\"", "");
        Locale loc = new Locale("en", country);
        String fullCountry = loc.getDisplayCountry();
        if (fullCountry.contains("States")) {
            fullCountry = "USA";
        }
        String city = json.get("name").toString().replace("\"", "");
        String weather =  json.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").toString().replace("\"", "");;
        String temperature = json.get("main").getAsJsonObject().get("temp").toString();
        String lon =  json.get("coord").getAsJsonObject().get("lon").toString();
        String lat = json.get("coord").getAsJsonObject().get("lat").toString();
        String wind = json.get("wind").getAsJsonObject().get("speed").toString();
        String pressure = json.get("main").getAsJsonObject().get("pressure").toString();
        String humidity = json.get("main").getAsJsonObject().get("humidity").toString();
        return new Forecast(fullCountry, city, weather, temperature, lon, lat, wind, pressure, humidity);
    }

    public static String suggestion(Forecast forecast) {
        StringBuilder sb = new StringBuilder();
        if (forecast.getWeather().equalsIgnoreCase("rain") || forecast.getWeather().equalsIgnoreCase("thunderstorm")) {
            sb.append("Do not forget umbrella, when going outside.\n");
        }
        return sb.toString();
    }

}
