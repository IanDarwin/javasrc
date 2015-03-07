package jndi;

import java.rmi.*;
import java.io.*;

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
