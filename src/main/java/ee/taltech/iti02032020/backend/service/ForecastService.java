package ee.taltech.iti02032020.backend.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.exception.PropertyNotFoundException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import ee.taltech.iti02032020.backend.request.ForecastRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    private ForecastRequest forecastRequest = new ForecastRequest();

    @Autowired
    private CoronaVirusService coronaViruses;

    @Autowired
    private ForecastRepository forecastRepository;

    public Forecast findById(Long id) {
        return forecastRepository.findById(id)
                .orElseThrow(PropertyNotFoundException::new);
    }

    public void save(Forecast forecast) throws IOException {
        if (forecast.getCountryName() == null || forecast.getCity() == null || forecast.getLat() == null
                || forecast.getLon() == null) {
            throw new PropertyNotFoundException();
        }
        if (forecast.getId() != null){
            throw new PropertyNotFoundException();
        }
        String forecastInfo = forecastRequest.ForecastRequestCity(forecast.getCity());
        JsonObject json = new Gson().fromJson(forecastInfo, JsonObject.class);
        int cod = json.get("cod").getAsInt();
        if (cod != 200) {
            CoronaVirus coronaVirus = coronaViruses.getCoronaVirus(forecast.getCountryName());
            Optional<CoronaVirus> coronaFromSet = ForecastService.this.checkCoronaInDatabase(forecast);
            if (coronaFromSet.isPresent()) {
                coronaViruses.update(coronaVirus, coronaFromSet.get().getId());
                forecast.setCoronaVirus(coronaFromSet.get());
            } else {
                coronaViruses.save(coronaVirus);
                forecast.setCoronaVirus(coronaVirus);
            }
            forecast.setNumOfSearches(1);
            forecastRepository.save(forecast);
        } else {
            throw new CityNotFoundException();
        }
    }

    public Forecast update(Forecast forecast, Long id) {
        if (forecast.getCountryName() == null || forecast.getCity() == null || forecast.getLat() == null
                || forecast.getLon() == null || forecast.getTemperature() == null || forecast.getWeather() == null
                || forecast.getWind() == null || forecast.getPressure() == null || forecast.getHumidity() == null) {
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
        dbForecast.setSuggestion(forecast.getSuggestion());
        dbForecast.setNumOfSearches(dbForecast.getNumOfSearches() + 1);
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
            CoronaVirus coronaVirus = coronaViruses.getCoronaVirus(forecast.getCountryName());
            int size = listFromDatabase.size();
            if (size > 0) {
                Optional<Forecast> forecastFromSet = listFromDatabase.parallelStream().filter(x -> x.getCity().equals(forecast.getCity())).findFirst();
                if (forecastFromSet.isPresent()) {
                    coronaViruses.update(coronaVirus, forecastFromSet.get().getCoronaVirus().getId());
                    forecast.setSuggestion(Forecast.suggestion(forecast));
                    return this.update(forecast, forecastFromSet.get().getId());
                }
            }
            Optional<CoronaVirus> coronaFromSet = ForecastService.this.checkCoronaInDatabase(forecast);
            if (coronaFromSet.isPresent()) {
                coronaViruses.update(coronaVirus, coronaFromSet.get().getId());
                forecast.setCoronaVirus(coronaViruses.findById(coronaFromSet.get().getId()));
                forecast.setSuggestion(Forecast.suggestion(forecast));
                forecast.setNumOfSearches(1);
                forecastRepository.save(forecast);
                return forecast;
            }
            coronaViruses.save(coronaVirus);
            forecast.setCoronaVirus(coronaVirus);
            forecast.setSuggestion(Forecast.suggestion(forecast));
            forecast.setNumOfSearches(1);
            forecastRepository.save(forecast);
            return forecast;
        } else {
            return ForecastService.this.forecastFromDatabase(city);
        }
    }

    public Optional<CoronaVirus> checkCoronaInDatabase(Forecast forecast) {
        List<CoronaVirus> listFromCoronaViruses = coronaViruses.findAll();
        return listFromCoronaViruses.parallelStream().filter(x -> x.getCountryName().equalsIgnoreCase(forecast.getCountryName())).findFirst();
    }

    public List<DailyForecast> getForecastFiveDays(String lon, String lat) throws IOException {
        if (lat != null && lon != null) {
            String forecastInfo = forecastRequest.ForecastRequestAll(Double.parseDouble(lon), Double.parseDouble(lat));
            return DailyForecast.getForecastFromJsonWeek(forecastInfo);
        } else {
            throw new PropertyNotFoundException();
        }
    }

    public List<String> topFiveSearches() {
        List<String> topFive = new ArrayList<>();
        List<Forecast> top = forecastRepository.findAll().stream().sorted(Comparator.comparingInt(Forecast::getNumOfSearches).reversed()).collect(Collectors.toList());
        if (top.size() > 5) {
            for (int i = 0; i < 5; i++) {
                topFive.add(top.get(i).getCity());
            }
        } else if (top.size() > 0){
            for (Forecast forecast : top) {
                topFive.add(forecast.getCity());
            }
        }
        return topFive;
    }

    public Forecast forecastFromDatabase(String city) {
        Optional<Forecast> forecast = forecastRepository.findAll().stream().filter(x -> x.getCity().equalsIgnoreCase(city.toUpperCase())).findFirst();
        if (forecast.isPresent()) {
            forecast.get().setNumOfSearches(forecast.get().getNumOfSearches() + 1);
            forecastRepository.save(forecast.get());
            return forecast.get();
        }
        return null;
    }

}
