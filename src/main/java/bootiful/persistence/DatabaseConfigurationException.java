package bootiful.persistence;

public class DatabaseConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseConfigurationException(String message) {
		super(message);
	}

	public DatabaseConfigurationException(String message, Throwable e) {
		super(message, e);
	}

}
