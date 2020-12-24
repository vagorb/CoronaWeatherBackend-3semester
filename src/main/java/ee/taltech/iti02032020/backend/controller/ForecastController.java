package ee.taltech.iti02032020.backend.controller;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.security.Roles;
import ee.taltech.iti02032020.backend.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
@RequestMapping("Forecast")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping("{id}")
    public Forecast getForecast(@PathVariable Long id) {
        return forecastService.findById(id);
    }

    // For old login form usage ALLOWED ONLY FOR LOGGED IN USERS
    @Secured(Roles.USER)
    @PostMapping
    public void saveForecast(@RequestBody Forecast forecast) throws IOException {
        forecastService.save(forecast);
    }

    @PutMapping("{id}")
    public Forecast updateForecast(@RequestBody Forecast forecast, @PathVariable Long id) {
        return forecastService.update(forecast, id);
    }

    @DeleteMapping("{id}")
    public void deleteForecast(@PathVariable Long id) {
        forecastService.delete(id);
    }

    @GetMapping("city/{city}")
    public Forecast getForecastByCity(@PathVariable String city) throws IOException {
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

    @Secured(Roles.USER)
    @GetMapping("user")
    public String user() {
        return "USER";
    }

    @Secured(Roles.ADMIN)
    @GetMapping("admin")
    public String admin() {
        return "Admin";
    }

}
