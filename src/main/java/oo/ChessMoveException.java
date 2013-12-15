package oo;

// BEGIN main
/** A ChessMoveException is thrown  when the user makes an illegal move. */
public class ChessMoveException extends Exception {

	private static final long serialVersionUID = 802911736988179079L;

	public ChessMoveException () {
		super();
	}
	
	public ChessMoveException (String msg) {
		super(msg);
	}
	
	public ChessMoveException(String msg, Exception cause) {
		super(msg, cause);
	}
}
// END main
