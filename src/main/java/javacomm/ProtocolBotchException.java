package javacomm;

/** An unchecked exception to indicate a protocol failure. */
public class ProtocolBotchException extends RuntimeException {
	ProtocolBotchException() {
		super();
	}
	ProtocolBotchException(String message) {
		super(message);
	}
}
