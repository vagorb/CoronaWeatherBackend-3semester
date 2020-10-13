package ee.taltech.iti02032020.backend.model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private String suggestion;
    private Integer numOfSearches;
    private String upToDate;
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

    public static Forecast getForecastFromJson(String stringJson) {
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
        double temperature = Double.parseDouble(forecast.getTemperature());
        if(temperature >= 23) {
            sb.append("It is really hot. Do not forget some water and wear cap outside your house.\n");
        } else if (temperature < 23 && temperature >= 15) {
            sb.append("It is really comfortable temperature. You can wear whatever you want in reasonable limits.\n");
        } else if (temperature < 15 && temperature >= 7) {
            sb.append("It is not really cold, but make sure to choose warmer clothes and wear some jacket.\n");
        } else if (temperature < 7 && temperature >= 0) {
            sb.append("It is cold, but not freezing. Wear worm clothes. Do not forget to change tires on your car soon.\n");
        } else if (temperature < 0 && temperature >= -10) {
            sb.append("It is cold. Wear warm clothes. Be aware of ice and check that your car had winter tires.\n");
        } else if (temperature < -10 && temperature >= -19) {
            sb.append("It is really freezing, but still not critical and with proper clothes, you can still go for the walk.\n");
        } else if (temperature < -19) {
            sb.append("It is too cold. Stay better at home and move only using vehicles if you have necessity to go outside.\n");
        }
        if (forecast.getWeather().equalsIgnoreCase("rain") || forecast.getWeather().equalsIgnoreCase("thunderstorm")
        || forecast.getWeather().equalsIgnoreCase("drizzle")) {
            sb.append("Do not forget umbrella, when going outside.\n");
        } else if (forecast.getWeather().equalsIgnoreCase("smoke") || forecast.getWeather().equalsIgnoreCase("dust")
        || forecast.getWeather().equalsIgnoreCase("ash") || forecast.getWeather().equalsIgnoreCase("sand")) {
            sb.append("Do not forget mask, when going outside.\n");
        } else if (forecast.getWeather().equalsIgnoreCase("squall") || forecast.getWeather().equalsIgnoreCase("tornado")) {
            sb.append("Stay at home it is too dangerous outside, because wind is too strong.\n");
        } else if (forecast.getWeather().equalsIgnoreCase("fog") || forecast.getWeather().equalsIgnoreCase("haze")
        || forecast.getWeather().equalsIgnoreCase("mist")) {
            sb.append("Be aware when going outside, because visibility could be really bad.\n");
        } else if (forecast.getWeather().equalsIgnoreCase("clear") || forecast.getWeather().equalsIgnoreCase("clouds")) {
            sb.append("It is nice weather without rain or snow.\n");
        } else if (forecast.getWeather().equalsIgnoreCase("snow")) {
            sb.append("Do not forget to cover your head, because it is snowing.\n");
        }
        sb.append("Good luck and stay safe, thanks for using CoronaWeather.");
        return sb.toString();
    }
}
