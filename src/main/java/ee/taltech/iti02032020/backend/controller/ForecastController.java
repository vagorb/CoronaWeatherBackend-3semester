package ee.taltech.iti02032020.backend.controller;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import ee.taltech.iti02032020.backend.request.ForecastRequest;
import ee.taltech.iti02032020.backend.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("Weather")
@RestController
public class ForecastController {

    private ForecastRequest forecastRequest = new ForecastRequest();

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastService forecastService;

    @GetMapping
    public List<Forecast> getCoronaViruses() {
        return forecastService.findAll();
    }

    @GetMapping("country/{country}")
    public Forecast getForecastByCity(@PathVariable String city) throws IOException {
        return Forecast.getForecastFromJson(city);
    }

    @GetMapping("{id}")
    public Forecast getForecast(@PathVariable Long id) {
        return forecastService.findById(id);
    }

    @PostMapping
    public void saveForecast(@RequestBody String city) throws IOException {
        String forecastInfo = forecastRequest.ForecastRequestCity(city);
        Forecast forecast = Forecast.getForecastFromJson(forecastInfo);
        forecastRepository.save(forecast);
    }

    @PutMapping("{id}")
    public Forecast updateForecast(@RequestBody Forecast forecast, @PathVariable Long id) {
        return forecastService.update(forecast, id);
    }

    @DeleteMapping("{id}")
    public void deleteForecast(@PathVariable Long id) {
        forecastService.delete(id);
    }
}
