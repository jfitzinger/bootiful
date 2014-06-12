package bootiful;

import java.net.URI;
import java.net.URISyntaxException;

public class DatabaseUrlFormatter {

	private static DatabaseUrlFormatter instance = null;

	protected DatabaseUrlFormatter() {
	}

	public static DatabaseUrlFormatter getInstance() {
		if (instance == null) {
			instance = new DatabaseUrlFormatter();
		}
		return instance;
	}

	public String checkAndCorrect(String databaseUrl) throws URISyntaxException {
		if (databaseUrl!=null && isHerokuPostgres(databaseUrl)) {
			return reformatHerokuPostgresUrl(new URI(databaseUrl));
		}
		return databaseUrl;
	}
	


	private static boolean isHerokuPostgres(String url) {
		return url.startsWith("postgres");
	}

	private static String reformatHerokuPostgresUrl(URI url) {
		StringBuffer correctUrl = new StringBuffer("jdbc:postgresql://");
		correctUrl.append(url.getHost()).append(':').append(url.getPort());
		correctUrl.append(url.getPath()).append("?username=")
				.append(url.getUserInfo().split(":")[0]);
		correctUrl.append("&password=").append(url.getUserInfo().split(":")[1]);
		return correctUrl.toString();
	}

}
