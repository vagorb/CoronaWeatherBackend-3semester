package ee.taltech.iti02032020.backend.service;

import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.exception.InvalidCountryException;
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
                .orElseThrow(CityNotFoundException::new);
    }

    public Forecast save(Forecast forecast) {
        if (forecast.getCountryName() == null || forecast.getCity() == null || forecast.getLat() == null
                || forecast.getLon() == null || forecast.getTemperature() == null || forecast.getWeather() == null
                || forecast.getWind() == null) {
            throw new InvalidCountryException();
        }
        if (forecast.getId() != null){
            throw new InvalidCountryException();
        }
        return forecastRepository.save(forecast);
    }

    public Forecast update(Forecast forecast, Long id) {
        if (forecast.getCountryName() == null) {
            throw new InvalidCountryException();
        }
        Forecast dbForecast = findById(id);
        dbForecast.setCountryName(forecast.getCountryName());
        dbForecast.setCity(forecast.getCity());
        dbForecast.setWeather(forecast.getWeather());
        dbForecast.setTemperature(forecast.getTemperature());
        dbForecast.setLon(forecast.getLon());
        dbForecast.setLat(forecast.getLat());
        dbForecast.setWind(forecast.getWind());
        dbForecast.setHumidity(forecast.getHumidity());
        dbForecast.setPressure(forecast.getPressure());
        return forecastRepository.save(dbForecast);
    }

    public void delete(Long id) {
        Forecast dbForecast = findById(id);
        forecastRepository.delete(dbForecast);
    }


}
