package ee.taltech.iti02032020.backend.request;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;


public class CoronaRequest {

    public CoronaRequest() {

    }
    public String CoronaRequestCountry(String country) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://coronavirus-map.p.rapidapi.com/v1/summary/region?region=" + country)
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "93172dfd82mshaeae41651c77f61p1382d2jsn6fe87dae6648")
                .build();

        return client.newCall(request).execute().body().string();
    }
}
