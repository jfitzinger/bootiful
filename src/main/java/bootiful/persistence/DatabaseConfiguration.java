package bootiful.persistence;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	private static final String DATABASE_URL_PROPERTY = "DATABASE_URL";

	@Bean
	public BasicDataSource dataSource() throws DatabaseConfigurationException {
		URI dbUri = validateUrl(getDatabaseUrlEnvironmentVariable());
		return constructDatasource(dbUri);
	}

	private BasicDataSource constructDatasource(URI dbUri) {
		BasicDataSource basicDataSource = new BasicDataSource();
		String[] userInfo = dbUri.getUserInfo().split(":");
		if (userInfo != null) {
			if (userInfo.length > 0) {
				basicDataSource.setUsername(userInfo[0]);
			}
			if (userInfo.length > 1) {
				basicDataSource.setPassword(userInfo[1]);
			}
		}
		basicDataSource.setUrl("jdbc:postgresql://" + dbUri.getHost()
				+ (dbUri.getPort() == -1 ? "" : ":" + dbUri.getPort())
				+ dbUri.getPath());
		return basicDataSource;
	}

	private URI validateUrl(String url) throws DatabaseConfigurationException {
		URI dbUri;
		if (url == null) {
			throw new DatabaseConfigurationException(
					String.format(
							"No database url from environement variable '%s'",DATABASE_URL_PROPERTY));
		}
		try {
			dbUri = new URI(url);
		} catch (URISyntaxException e) {
			throw new DatabaseConfigurationException(
					String.format(
							"Error parsing database url '%s' from environement variable '%s'",
							url, DATABASE_URL_PROPERTY), e);
		}
		if (dbUri.getUserInfo() == null) {
			throw new DatabaseConfigurationException(
					String.format(
							"Error parsing database url '%s' from environement variable '%s' - no user information provided",
							url, DATABASE_URL_PROPERTY));

		}
		return dbUri;
	}

	protected String getDatabaseUrlEnvironmentVariable() {
		return System.getenv(DATABASE_URL_PROPERTY);
	}

}
