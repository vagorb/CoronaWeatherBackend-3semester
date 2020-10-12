package ee.taltech.iti02032020.backend.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.exception.PropertyNotFoundException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import ee.taltech.iti02032020.backend.request.ForecastRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ForecastService {

    private ForecastRequest forecastRequest = new ForecastRequest();

    @Autowired
    private CoronaVirusService coronaViruses;

    @Autowired
    private ForecastRepository forecastRepository;

    public List<Forecast> findAll() {
        return forecastRepository.findAll();
    }

    public Forecast findById(Long id) {
        return forecastRepository.findById(id)
                .orElseThrow(PropertyNotFoundException::new);
    }

    public Forecast save(Forecast forecast) {
        if (forecast.getCountryName() == null || forecast.getCity() == null || forecast.getLat() == null
                || forecast.getLon() == null || forecast.getTemperature() == null || forecast.getWeather() == null
                || forecast.getWind() == null) {
            throw new PropertyNotFoundException();
        }
        if (forecast.getId() != null){
            throw new PropertyNotFoundException();
        }
        return forecastRepository.save(forecast);
    }

    public Forecast update(Forecast forecast, Long id) {
        if (forecast.getCountryName() == null || forecast.getCity() == null || forecast.getLat() == null
                || forecast.getLon() == null || forecast.getTemperature() == null || forecast.getWeather() == null
                || forecast.getWind() == null) {
            throw new PropertyNotFoundException();
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

    public Forecast getForecastByCity(String city) throws IOException {
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
                Optional<Forecast> forecastFromSet = listFromDatabase.parallelStream().filter(x -> x.getCity().equals(forecast.getCity())).findFirst();
                if (forecastFromSet.isPresent()) {
                    coronaViruses.update(coronaVirus, forecastFromSet.get().getCoronaVirus().getId());
                    forecast.setSuggestion(Forecast.suggestion(forecast));
                    return this.update(forecast, forecastFromSet.get().getId());
                }
            }
            List<CoronaVirus> listFromCoronaViruses = coronaViruses.findAll();
            Optional<CoronaVirus> coronaFromSet = listFromCoronaViruses.parallelStream().filter(x -> x.getCountryName().equals(forecast.getCountryName())).findFirst();
            if (coronaFromSet.isPresent()) {
                coronaViruses.update(coronaVirus, coronaFromSet.get().getId());
                forecast.setCoronaVirus(coronaViruses.findById(coronaFromSet.get().getId()));
                forecast.setSuggestion(Forecast.suggestion(forecast));
                ForecastService.this.save(forecast);
                return forecast;
            }
            coronaViruses.save(coronaVirus);
            forecast.setCoronaVirus(coronaViruses.findById((long) size + listFromCoronaViruses.size() + 1));
            forecast.setSuggestion(Forecast.suggestion(forecast));
            ForecastService.this.save(forecast);
            return forecast;
        } else {
            throw new CityNotFoundException();
        }
    }

    public List<DailyForecast> getForecastFiveDays(String lon, String lat) throws IOException {
        if (lat != null && lon != null) {
            String forecastInfo = forecastRequest.ForecastRequestAll(Double.parseDouble(lon), Double.parseDouble(lat));
            return DailyForecast.getForecastFromJsonWeek(forecastInfo);
        } else {
            throw new PropertyNotFoundException();
        }
    }


}
