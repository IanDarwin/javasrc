package oo;

/** A ChessMoveException is thrown  when the user makes an illegal move. */
public class ChessMoveException extends Exception {
	public ChessMoveException () {
		super();
	}
	public ChessMoveException (String msg) {
		super(msg);
	}
}
