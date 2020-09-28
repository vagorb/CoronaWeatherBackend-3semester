package ee.taltech.iti02032020.backend.service;

import ee.taltech.iti02032020.backend.exception.CoronaVirusCountryNotFoundException;
import ee.taltech.iti02032020.backend.exception.InvalidCoronaVirusCountryException;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForecastService {

    @Autowired
    private ForecastRepository forecastRepository;

    public List<Forecast> findAll() {
        return forecastRepository.findAll();
    }

    public Forecast findById(Long id) {
        return forecastRepository.findById(id)
                .orElseThrow(CoronaVirusCountryNotFoundException::new);
    }

    //     Add exception messages !!!
    public Forecast save(Forecast forecast) {
        if (forecast.getCountryName() == null) {
            throw new InvalidCoronaVirusCountryException();
        }
        if (forecast.getId() != null){
            throw new InvalidCoronaVirusCountryException();
        }

        // save will generate id for object
        return forecastRepository.save(forecast);
    }

    public Forecast update(Forecast forecast, Long id) {
        if (forecast.getCountryName() == null) {
            throw new InvalidCoronaVirusCountryException();
        }
        Forecast dbForecast = findById(id);
        dbForecast.setCountryName(forecast.getCountryName());
        dbForecast.setCity(forecast.getCity());
        dbForecast.setWeather(forecast.getWeather());
        dbForecast.setTemperature(forecast.getTemperature());
        dbForecast.setLon(forecast.getLon());
        dbForecast.setLat(forecast.getLat());
        dbForecast.setWind(forecast.getWind());
        return forecastRepository.save(dbForecast);
    }

    public void delete(Long id) {
        Forecast dbForecast = findById(id);
        forecastRepository.delete(dbForecast);
    }
}
