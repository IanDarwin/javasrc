package tar;

/*
 * Exception for TarFile and TarEntry.
 * $Id$
 */
public class TarException extends java.io.IOException {
	private static final long serialVersionUID = 1L;
	public TarException() {
		super();
	}
	public TarException(String msg) {
		super(msg);
	}
}
