package bootiful;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	private static final String DATABASE_URL_PROPERTY = "database.url";

	public static void main(String[] args) throws URISyntaxException {
		System.setProperty(DATABASE_URL_PROPERTY,DatabaseUrlFormatter.getInstance()
				.checkAndCorrect(System.getProperty(DATABASE_URL_PROPERTY)));
		SpringApplication.run(Application.class, args);
	}


}
