package ee.taltech.iti02032020.backend.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class WeatherRequest {


    public Response WeatherRequestCity() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(("http://api.openweathermap.org/data/2.5/weather?q=London&appid=8a407506c2636871c9fafed41eb6889e"))
                .build();


        return client.newCall(request).execute();
    }
}
