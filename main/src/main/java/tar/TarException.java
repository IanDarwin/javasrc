package tar;

/*
 * Exception for TarFile and TarEntry.
 * $Id$
 */
public class TarException extends java.io.IOException {
	public TarException() {
		super();
	}
	public TarException(String msg) {
		super(msg);
	}
}
