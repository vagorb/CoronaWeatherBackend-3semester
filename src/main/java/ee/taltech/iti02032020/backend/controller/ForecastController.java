package ee.taltech.iti02032020.backend.controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import ee.taltech.iti02032020.backend.request.ForecastRequest;
import ee.taltech.iti02032020.backend.service.CoronaVirusService;
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
@RequestMapping("Forecast")
@RestController
public class ForecastController {
    private ForecastRequest forecastRequest = new ForecastRequest();

    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    @Autowired
    private CoronaVirusService coronaViruses;

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastService forecastService;

    @GetMapping
    public List<Forecast> getForecasts() {
        return forecastService.findAll();
    }

    @GetMapping("{id}")
    public Forecast getForecast(@PathVariable Long id) {
        return forecastService.findById(id);
    }

    @PostMapping
    public void saveForecast(@RequestBody Forecast forecast) {
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
        String forecastInfo = forecastRequest.ForecastRequestCity(city);
        JsonObject json = new Gson().fromJson(forecastInfo, JsonObject.class);
        int cod = json.get("cod").getAsInt();
        if (cod == 200) {
            Forecast forecast = Forecast.getForecastFromJson(forecastInfo);
            List<Forecast> listFromDatabase = forecastRepository.findAll();
            CoronaRequest coronaRequest = new CoronaRequest();
            String coronaInfo = coronaRequest.CoronaRequestCountry(forecast.getCountryName());
            CoronaVirus coronaVirus = CoronaVirus.getCoronaVirusFromJson(coronaInfo, forecast.getCountryName());
            int size = listFromDatabase.size();
            if (size > 0) {
                for (Forecast forecasts : listFromDatabase) {
                    if (forecasts.getCity().equals(forecast.getCity())) {
                        coronaViruses.update(coronaVirus, forecasts.getCoronaVirus().getId());
                        forecastService.update(forecast, forecasts.getId());
                        return forecast;
                    }
                }
            }
            List<CoronaVirus> listFromCoronaViruses = coronaVirusRepository.findAll();
            for (CoronaVirus corona : listFromCoronaViruses) {
                if (corona.getCountryName().equals(forecast.getCountryName())) {
                    coronaViruses.update(coronaVirus, corona.getId());
                    forecast.setCoronaVirus(coronaViruses.findById(corona.getId()));
                    forecastService.save(forecast);
                    return forecast;
                }
            }
            coronaViruses.save(coronaVirus);
            forecast.setCoronaVirus(coronaViruses.findById((long) size + listFromCoronaViruses.size()));
            forecastService.save(forecast);
            return forecast;
        } else {
            throw new CityNotFoundException();
        }
    }
}
