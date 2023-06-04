package sc.alwe.trinacriasql.model;

import java.util.List;

/**
 * Exception thrown by TrinacriaSQL when an error occurs during query
 * parsing/execution.
 */
public class ChiFariException extends RuntimeException {

	public ChiFariException(List<String> expectedTokens, String actualToken) {
		super("Expected one of " + expectedTokens + " but got [" + actualToken + "]");
	}

	public ChiFariException(String expectedToken, String token) {
		super("Expected [" + expectedToken + "] but got [" + token + "]");
	}

	public ChiFariException(String message) {
		super(message);
	}

	public ChiFariException(Exception e) {
		super(e);
	}

}
