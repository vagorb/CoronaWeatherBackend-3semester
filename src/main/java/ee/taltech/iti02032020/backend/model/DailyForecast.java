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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyForecast {

    @Id
    @GeneratedValue
    private Long id;
    private String day;
    private String night;
    private String weather;
    private String pressure;
    private String humidity;
    private String wind;

    public DailyForecast(String day, String night, String weather, String pressure, String humidity, String wind) {
        this.day = day;
        this.night = night;
        this.weather = weather;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
    }

    public static List<DailyForecast> getForecastFromJsonWeek(String stringJson) {
        ArrayList<DailyForecast> week = new ArrayList<>();
        JsonObject json = new Gson().fromJson(stringJson, JsonObject.class);
        for (int i = 0; i < 8; i++) {
            String day = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("day").toString();
            String night = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("night").toString();
            String weather = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").toString().replace("\"", "");
            String pressure = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("pressure").toString();
            String humidity = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("humidity").toString();
            String wind = json.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("wind_speed").toString();
            DailyForecast forecast = new DailyForecast(day, night, weather, pressure, humidity, wind);
            week.add(forecast);
        }
        return week;
    }
}
