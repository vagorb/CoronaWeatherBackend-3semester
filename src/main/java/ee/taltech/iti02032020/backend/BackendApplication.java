package ee.taltech.iti02032020.backend;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.request.ForecastRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Locale;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) throws IOException {
		Locale loc = new Locale("en");
		java.util.Locale.setDefault(loc);
//		ForecastRequest forecastRequest = new ForecastRequest();
//		String info = forecastRequest.ForecastRequestAll(13.41, 52.52);
//		System.out.println(DailyForecast.getForecastFromJsonWeek(info));
//		System.out.println(info);
//		JsonObject json = new Gson().fromJson(info, JsonObject.class);
//		String weather =  json.get("daily").getAsJsonArray().get(0).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").toString();
//		String weather1 =  json.get("daily").getAsJsonArray().get(1).toString();
//		String weather2 =  json.get("daily").getAsJsonArray().get(2).toString();
//		String weather3 =  json.get("daily").getAsJsonArray().get(3).toString();
//		String weather4 =  json.get("daily").getAsJsonArray().get(4).toString();
//		String weather5 =  json.get("daily").getAsJsonArray().get(5).toString();
//		String weather6 =  json.get("daily").getAsJsonArray().get(6).toString();
//		String weather7 =  json.get("daily").getAsJsonArray().get(7).toString();
//
//		System.out.println(weather);
//		System.out.println(weather1);
//		System.out.println(weather2);
//		System.out.println(weather3);
//		System.out.println(weather4);
//		System.out.println(weather5);
//		System.out.println(weather6);
//		System.out.println(weather7);

//		CoronaRequest coronaRequest = new CoronaRequest();
//		coronaRequest.CoronaRequestCountry();
//		System.out.println(coronaRequest.CoronaRequestCountry("Estonia"));
//		coronaRequest.addRequestToDatabase(coronaRequest.CoronaRequestCountry("Estonia"), "Estonia");
//		coronaRequest.addRequestToDatabase(coronaRequest.CoronaRequestCountry("Estonia"), "Estonia");
//		coronaRequest.CoronaRequestCountry("Estonia");
//		System.out.println(coronaRequest.anotherAPI());
//		System.out.println(coronaRequest.getInfoFromResponse(coronaRequest.CoronaRequestCountry()));
//		WeatherRequest request = new WeatherRequest();
//		Response req = request.WeatherRequestCity();
//		System.out.println(req);
		SpringApplication.run(BackendApplication.class, args);
	}

}
