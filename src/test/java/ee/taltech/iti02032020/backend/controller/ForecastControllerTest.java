package ee.taltech.iti02032020.backend.controller;

import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ForecastControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    public static final ParameterizedTypeReference<List<String>> LIST_OF_FORECASTS = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<List<DailyForecast>> LIST_OF_FORECASTS_FOR_SEVEN_DAYS = new ParameterizedTypeReference<>() {
    };

    @Test
    void deleteFromDatabase() {
        Forecast forecast1 = new Forecast("Estonia", "CityDoNotExistInDatabase1",null, null, "45", "45", null, null, null);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, new HttpEntity<>(forecast1), Forecast.class);
        testRestTemplate.exchange("/Forecast/2", HttpMethod.DELETE, null, Forecast.class);
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
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, new HttpEntity<>(forecast1), Forecast.class);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, new HttpEntity<>(forecast2), Forecast.class);
        testRestTemplate.exchange("/Forecast", HttpMethod.POST, new HttpEntity<>(forecast4), Forecast.class);
        testRestTemplate.exchange("/Forecast/city/CityDoNotExistInDatabase4", HttpMethod.GET, null, Forecast.class);
        testRestTemplate.exchange("/Forecast/city/CityDoNotExistInDatabase4", HttpMethod.GET, null, Forecast.class);
        ResponseEntity<List<String>> exchange = testRestTemplate.exchange("/Forecast",
                HttpMethod.GET, null, LIST_OF_FORECASTS);
        List<String> cities = assertOk(exchange);
        assertEquals("CityDoNotExistInDatabase4", cities.get(0));
    }

    @Test
    void saveOrUpdateInfoFromExternalAPIInDatabase() {
        testRestTemplate.exchange("/Forecast/city/sitka",
                HttpMethod.GET, null, Forecast.class);
        testRestTemplate.exchange("/Forecast/city/fairbanks",
                HttpMethod.GET, null, Forecast.class);
        ResponseEntity<Forecast> exchange = testRestTemplate.exchange("/Forecast/city/fairbanks",
                HttpMethod.GET, null, Forecast.class);
        Forecast forecast = assertOk(exchange);
        assertEquals("Fairbanks", forecast.getCity());
        assertEquals("USA", forecast.getCoronaVirus().getCountryName());
    }

    @Test
    void getForecastFiveDays() {
        ResponseEntity<List<DailyForecast>> exchange = testRestTemplate.exchange("/Forecast/lat/50/lon/50",
                HttpMethod.GET, null, LIST_OF_FORECASTS_FOR_SEVEN_DAYS);
        List<DailyForecast> forecasts = assertOk(exchange);
        assertEquals(8, forecasts.size());
    }


    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}