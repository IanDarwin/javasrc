package email;

/** This is like an IOException, but it includes a Sendmail-style error
 * number in addition to the error message String.
 */
public class SMTPException extends java.io.IOException {
	int ret;

	SMTPException(int ret, String s) {
		super(s);
		this.ret = ret;
	}
	int getCode() {
		return ret;
	}
}
