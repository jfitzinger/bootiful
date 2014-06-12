package bootiful;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

public class DatabaseUrlFormatterTest {
	
	DatabaseUrlFormatter databaseUrlFormatter = DatabaseUrlFormatter.getInstance();

	@Test
	public void shouldReturnCorrectJdbcUrl() throws URISyntaxException {
		//given
		String wrongFormatUrl="postgres://foo:foo1@heroku.com:5432/hellodb";
		//when
		String reformatedActual = databaseUrlFormatter.checkAndCorrect(wrongFormatUrl);
		//then
		assertEquals("jdbc:postgresql://heroku.com:5432/hellodb?username=foo&password=foo1", reformatedActual);
	}

}
