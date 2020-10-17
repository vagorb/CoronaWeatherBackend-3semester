package ee.taltech.iti02032020.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Locale;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args){
		Locale loc = new Locale("en");
		java.util.Locale.setDefault(loc);
		SpringApplication.run(BackendApplication.class, args);
	}
}
