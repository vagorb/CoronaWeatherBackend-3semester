package ee.taltech.iti02032020.backend.controller;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.security.Roles;
import ee.taltech.iti02032020.backend.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
@RequestMapping("Forecast")
@RestController

public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @Secured(Roles.USER)
    @PostMapping
    public void saveForecast(@RequestBody Forecast forecast) throws IOException {
        forecastService.save(forecast);
    }


    @Secured(Roles.ADMIN)
    @DeleteMapping("{city}")
    public void deleteForecast(@PathVariable String city) {
        forecastService.delete(city);
    }


    @GetMapping("city")
    public Forecast getForecastByCity(@RequestParam String city) throws IOException {
        return forecastService.getForecastByCity(city);
    }

    @GetMapping("lat/{lat}/lon/{lon}")
    public List<DailyForecast> getForecastFiveDays(@PathVariable String lat, @PathVariable String lon) throws IOException {
        return forecastService.getForecastFiveDays(lon, lat);
    }

    @GetMapping
    public List<String> getTopFive() {
        return forecastService.topFiveSearches();
    }

}
