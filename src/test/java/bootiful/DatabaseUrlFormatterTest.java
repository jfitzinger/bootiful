package bootiful;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

public class DatabaseUrlFormatterTest {
	
	DatabaseUrlFormatter databaseUrlFormatter = DatabaseUrlFormatter.getInstance();

	@Test
	public void shouldReturnCorrectJdbcUrl() throws URISyntaxException {
		//given
		String wrongFormatUrl="postgres://rtpnrbwuwoawce:N_aN1FeUn90KA7uAIvtVl5XFpn@ec2-54-204-41-249.compute-1.amazonaws.com:5432/d7dhhf1dqfbjdg";
		//when
		String reformatedActual = databaseUrlFormatter.checkAndCorrect(wrongFormatUrl);
		//then
		assertEquals("jdbc:postgresql://ec2-54-204-41-249.compute-1.amazonaws.com:5432/d7dhhf1dqfbjdg?username=rtpnrbwuwoawce&password=N_aN1FeUn90KA7uAIvtVl5XFpn", reformatedActual);
	}

}
