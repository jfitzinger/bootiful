package bootiful.persistence;

import static org.junit.Assert.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import bootiful.persistence.DbConfig;

public class DbConfigTest {

	private String url;
	TestableDbConfig testableDbConfig = new TestableDbConfig();

	@Test
	public void shouldReturnCorrectDatasource()
			throws DatabaseConfigurationException {
		// given
		url = "postgres://johanna:secret@host:444/schema";
		// when
		BasicDataSource dataSource = testableDbConfig.dataSource();
		// then
		assertEquals("jdbc:postgresql://host:444/schema", dataSource.getUrl());
		assertEquals("johanna", dataSource.getUsername());
		assertEquals("secret", dataSource.getPassword());
	}

	@Test
	public void shouldReturnCorrectDatasourceForEmptyPort()
			throws DatabaseConfigurationException {
		// given
		url = "postgres://johanna:secret@host/schema";
		// when
		BasicDataSource dataSource = testableDbConfig.dataSource();
		// then
		assertEquals("jdbc:postgresql://host/schema", dataSource.getUrl());
		assertEquals("johanna", dataSource.getUsername());
		assertEquals("secret", dataSource.getPassword());
	}

	@Test(expected = DatabaseConfigurationException.class)
	public void shouldReturnException() throws DatabaseConfigurationException {
		// given
		url = "bjhuihu";
		// when
		testableDbConfig.dataSource();
	}

	@Test(expected = DatabaseConfigurationException.class)
	public void shouldReturnExceptionForNullUrl()
			throws DatabaseConfigurationException {
		// given
		url = null;
		// when
		testableDbConfig.dataSource();
	}

	private class TestableDbConfig extends DbConfig {

		protected String getDatabaseUrlEnvironmentVariable() {
			return url;
		}
	}

}
