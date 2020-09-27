package ee.taltech.iti02032020.backend.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;


public class ForecastRequest {


    public String ForecastRequestCity(String city) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=8a407506c2636871c9fafed41eb6889e"))
                .build();


        return client.newCall(request).execute().body().string();
    }

    public String ForecastRequestAll(Long lon, Long lat) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=metric&appid=8a407506c2636871c9fafed41eb6889e")
                .build();


        return client.newCall(request).execute().body().string();
    }
}
