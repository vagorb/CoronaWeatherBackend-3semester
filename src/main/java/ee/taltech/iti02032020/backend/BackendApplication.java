package ee.taltech.iti02032020.backend;

import ee.taltech.iti02032020.backend.request.CoronaRequest;
import ee.taltech.iti02032020.backend.request.WeatherRequest;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) throws IOException {
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
