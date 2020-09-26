package ee.taltech.iti02032020.backend.request;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class CoronaRequest {

    public CoronaRequest() throws IOException {

    }

    public Response CoronaRequestCountry() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://covid-19-data.p.rapidapi.com/country?format=json&name=estonia")
                .get()
                .addHeader("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "93172dfd82mshaeae41651c77f61p1382d2jsn6fe87dae6648")
                .build();

        return client.newCall(request).execute();
    }
}
