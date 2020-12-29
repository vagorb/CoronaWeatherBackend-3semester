package ee.taltech.iti02032020.backend.controller;

import ee.taltech.iti02032020.backend.controller.common.RestTemplate;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForecastControllerTest extends RestTemplate {

    public static final String USER = "user";
    public static final String ADMIN = "admin";

    @Test
    void deleteFromDatabase() {
        Forecast forecast1 = new Forecast("Estonia", "CityDoNotExistInDatabase1",null, null, "45", "45", null, null, null);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, entity(forecast1, ADMIN), Forecast.class);
        testRestTemplate.exchange("/Forecast/CityDoNotExistInDatabase1", HttpMethod.DELETE, entity(null, ADMIN), Forecast.class);
        ResponseEntity<List<String>> exchange = testRestTemplate.exchange("/Forecast",
                HttpMethod.GET, null, LIST_OF_FORECASTS);
        List<String> cities = assertOk(exchange);
        assertEquals(0, cities.size());
    }


    @Test
    void getTopFive() {
        Forecast forecast1 = new Forecast("Estonia", "CityDoNotExistInDatabase1",null, null, "45", "45", null, null, null);
        Forecast forecast2 = new Forecast("Estonia", "CityDoNotExistInDatabase2",null, null, "45", "45", null, null, null);
        Forecast forecast4 = new Forecast("Estonia", "CityDoNotExistInDatabase4",null, null, "45", "45", null, null, null);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, entity(forecast1, USER), Forecast.class);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, entity(forecast2, USER), Forecast.class);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, entity(forecast4, USER), Forecast.class);
        testRestTemplate.exchange("/Forecast/city?city=CityDoNotExistInDatabase4", HttpMethod.GET, null, Forecast.class);
        testRestTemplate.exchange("/Forecast/city?city=CityDoNotExistInDatabase4", HttpMethod.GET, null, Forecast.class);
        ResponseEntity<List<String>> exchange = testRestTemplate.exchange("/Forecast",
                HttpMethod.GET, null, LIST_OF_FORECASTS);
        List<String> cities = assertOk(exchange);
        assertEquals("CityDoNotExistInDatabase4", cities.get(0));
    }

    @Test
    void saveOrUpdateInfoFromExternalAPIInDatabase() {
        testRestTemplate.exchange("/Forecast/city?city=sitka",
                HttpMethod.GET, null, Forecast.class);
        testRestTemplate.exchange("/Forecast/city?city=fairbanks",
                HttpMethod.GET, null, Forecast.class);
        ResponseEntity<Forecast> exchange = testRestTemplate.exchange("/Forecast/city?city=fairbanks",
                HttpMethod.GET, null, Forecast.class);
        Forecast forecast = assertOk(exchange);
        assertEquals("Fairbanks", forecast.getCity());
        assertEquals("USA", forecast.getCoronaVirus().getCountryName());
    }

    @Test
    void getForecastFiveDays() {
        ResponseEntity<List<DailyForecast>> exchange = testRestTemplate.exchange("/Forecast/lat/50/lon/50",
                HttpMethod.GET, entity(null, USER), LIST_OF_FORECASTS_FOR_SEVEN_DAYS);
        List<DailyForecast> forecasts = assertOk(exchange);
        assertEquals(8, forecasts.size());
    }



    private <T> HttpEntity<T> entity(T msg, String username) {
        HttpHeaders headers = authorizationHeader(username);
        return new HttpEntity<>(msg, headers);
    }
}