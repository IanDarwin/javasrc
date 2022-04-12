package jndi;

import java.io.Serializable;
import java.rmi.Remote;

public class JNDIData implements Remote, Serializable {

	private static final long serialVersionUID = 1L;
	protected String message;
	public void setMessage(String m) {
	 message = m;
	}
	public String getMessage() {
			return message;
	}
}
