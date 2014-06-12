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
		checkAndCorrectDatabaseUrl();
		SpringApplication.run(Application.class, args);
	}

	private static void checkAndCorrectDatabaseUrl() throws URISyntaxException {
		String url = System.getProperty(DATABASE_URL_PROPERTY);
		if (url!=null && isHerokuPostgres(url)) {
			System.setProperty(DATABASE_URL_PROPERTY, reformatHerokuPostgresUrl(new URI(url)));
		}
	}

	private static boolean isHerokuPostgres(String url) {
		return url.startsWith("postgres");
	}

	private static String reformatHerokuPostgresUrl(URI url) {
		StringBuffer correctUrl = new StringBuffer("jdbc:postgresql://");
		correctUrl.append(url.getHost()).append(':').append(url.getPort());
		correctUrl.append(url.getPath()).append("?username=")
				.append(url.getUserInfo().split(":")[0]);
		correctUrl.append("?password=").append(url.getUserInfo().split(":")[1]);
		return correctUrl.toString();
	}

}
