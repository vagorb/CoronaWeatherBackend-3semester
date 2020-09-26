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
//		WeatherRequest request = new WeatherRequest();
//		Response req = request.WeatherRequestCity();
//		System.out.println(req);
		SpringApplication.run(BackendApplication.class, args);
	}

}
