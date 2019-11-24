package email;

/** This unchecked Exception includes an SMTP protocol error
 * number in addition to the error message String.
 */
public class SMTPException extends RuntimeException {

	private static final long serialVersionUID = -8631535349066609403L;
	int errno;

	SMTPException(int errno, String s) {
		super(s);
		this.errno =  errno;
	}
	
	int getCode() {
		return errno;
	}
}
